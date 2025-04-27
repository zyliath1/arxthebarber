package com.example.application.views.hallinta;

import com.example.application.data.Booking;
import com.example.application.services.BookingService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Varaukset Hallinta")
@Route("varaukset")
@RolesAllowed("ADMIN")
public class VarauksetView extends VerticalLayout {

    private final BookingService bookingService;
    private final Grid<Booking> grid = new Grid<>(Booking.class, false);

    public VarauksetView(BookingService bookingService) {
        this.bookingService = bookingService;

        setSpacing(true);
        setPadding(true);

        H2 otsikko = new H2("Kaikki varaukset");

        grid.addColumn(b -> b.getCustomer().getEtunimi() + " " + b.getCustomer().getSukunimi()).setHeader("Asiakas");
        grid.addColumn(b -> b.getService().getNimi()).setHeader("Palvelu");
        grid.addColumn(b -> b.getBarber().getNimi()).setHeader("Parturi");
        grid.addColumn(b -> b.getAika().toString()).setHeader("Aika");

        grid.setItems(bookingService.findAll());

        add(otsikko, grid);
    }
}
