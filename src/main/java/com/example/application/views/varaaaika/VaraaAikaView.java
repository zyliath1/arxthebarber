package com.example.application.views.varaaaika;

import com.example.application.data.*;
import com.example.application.security.AuthenticatedUser;
import com.example.application.services.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.RolesAllowed;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@PageTitle("Varaa aika")
@Route("varaaika")
@RolesAllowed("USER")
public class VaraaAikaView extends VerticalLayout {

    private final BookingService bookingService;
    private final ServiceItemService serviceItemService;
    private final BarberService barberService;
    private final CustomerService customerService;
    private final AuthenticatedUser authenticatedUser;

    private final Grid<Booking> grid = new Grid<>(Booking.class, false);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy 'klo' HH:mm");

    public VaraaAikaView(BookingService bookingService,
                         ServiceItemService serviceItemService,
                         BarberService barberService,
                         CustomerService customerService,
                         AuthenticatedUser authenticatedUser) {

        this.bookingService = bookingService;
        this.serviceItemService = serviceItemService;
        this.barberService = barberService;
        this.customerService = customerService;
        this.authenticatedUser = authenticatedUser;

        setSpacing(true);
        setPadding(true);

        H2 otsikko = new H2("Varaa aika parturille");

        ComboBox<ServiceItem> palvelut = new ComboBox<>("Palvelu");
        palvelut.setItems(serviceItemService.findAll());
        palvelut.setItemLabelGenerator(ServiceItem::getNimi);

        ComboBox<Barber> parturit = new ComboBox<>("Parturi");
        parturit.setItems(barberService.findAll());
        parturit.setItemLabelGenerator(barber -> {
            ServiceItem valittuPalvelu = palvelut.getValue();
            String hinta = valittuPalvelu != null ? String.format("%.2f €", valittuPalvelu.getHinta()) : "";
            return barber.getNimi() + " – " + barber.getTitteli() + (hinta.isEmpty() ? "" : " (" + hinta + ")");
        });

        palvelut.addValueChangeListener(event -> parturit.getDataProvider().refreshAll());

        DatePicker datePicker = new DatePicker("Päivämäärä");
        datePicker.setMin(LocalDate.now());
        datePicker.setAutoOpen(true);


        datePicker.addValueChangeListener(event -> {
            LocalDate selectedDate = event.getValue();
            if (selectedDate != null &&
                    (selectedDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                            selectedDate.getDayOfWeek() == DayOfWeek.SUNDAY)) {

                datePicker.setInvalid(true);
                datePicker.setErrorMessage("Valitse arkipäivä (ma–pe)");
                datePicker.clear(); // Voit myös poistaa tämän jos haluat säilyttää arvon näkyvillä
            } else {
                datePicker.setInvalid(false);
                datePicker.setErrorMessage(null);
            }
        });

        TimePicker timePicker = new TimePicker("Kellonaika");
        timePicker.setStep(Duration.ofMinutes(15));
        timePicker.setMin(LocalTime.of(10, 0));
        timePicker.setMax(LocalTime.of(18, 0));
        timePicker.setPlaceholder("Valitse aika");


        HorizontalLayout aikaValinta = new HorizontalLayout(datePicker, timePicker);

        Button varaa = new Button("Varaa aika", click -> {
            Optional<User> user = authenticatedUser.get();

            if (user.isPresent()) {
                Customer customer = customerService.findByUsername(user.get().getUsername()).orElse(null);

                if (customer != null &&
                        palvelut.getValue() != null &&
                        parturit.getValue() != null &&
                        datePicker.getValue() != null &&
                        timePicker.getValue() != null) {

                    LocalDateTime valittuAika = LocalDateTime.of(datePicker.getValue(), timePicker.getValue());

                    // 🔒 Estetään kaksoisvaraus
                    boolean onJoVarattu = bookingService.findAll().stream()
                            .anyMatch(b ->
                                    b.getBarber().getId().equals(parturit.getValue().getId()) &&
                                            b.getAika().equals(valittuAika)
                            );

                    if (onJoVarattu) {
                        Notification.show("Valittu aika on jo varattu tälle parturille!", 3000, Notification.Position.MIDDLE);
                        return;
                    }

                    Booking booking = new Booking();
                    booking.setCustomer(customer);
                    booking.setService(palvelut.getValue());
                    booking.setBarber(parturit.getValue());
                    booking.setAika(valittuAika);

                    bookingService.save(booking);

                    Notification.show("Aika varattu onnistuneesti!", 3000, Notification.Position.MIDDLE);

                    datePicker.clear();
                    timePicker.clear();
                    refreshGrid(customer);
                } else {
                    Notification.show("Täytä kaikki kentät ennen varausta.", 3000, Notification.Position.MIDDLE);
                }
            }
        });

        grid.addColumn(b -> b.getService().getNimi()).setHeader("Palvelu");
        grid.addColumn(b -> b.getBarber().getNimi()).setHeader("Parturi");
        grid.addColumn(booking -> booking.getAika().format(formatter)).setHeader("Aika");

        add(otsikko, palvelut, parturit, aikaValinta, varaa, grid);

        authenticatedUser.get()
                .flatMap(user -> customerService.findByUsername(user.getUsername()))
                .ifPresent(this::refreshGrid);
    }

    private void refreshGrid(Customer customer) {
        grid.setItems(bookingService.findAll().stream()
                .filter(b -> b.getCustomer().getId().equals(customer.getId()))
                .toList());
    }
}
