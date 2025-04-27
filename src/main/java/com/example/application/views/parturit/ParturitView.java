package com.example.application.views.parturit;

import com.example.application.data.Barber;
import com.example.application.services.BarberService;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

@PageTitle("Parturit")
@Route("parturit")
@AnonymousAllowed
public class ParturitView extends Main implements HasComponents, HasStyle {

    private final BarberService barberService;
    private OrderedList parturitList;

    public ParturitView(BarberService barberService) {
        this.barberService = barberService;
        constructUI();
        listaaParturit();
    }

    private void constructUI() {
        addClassNames("parturit-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        H2 header = new H2("Parturit");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.XLARGE, FontSize.XXXLARGE);

        parturitList = new OrderedList();
        parturitList.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        add(header, parturitList);
    }

    private void listaaParturit() {
        barberService.findAll().forEach(barber -> {
            parturitList.add(new ParturitViewCard(barber));
        });
    }
}
