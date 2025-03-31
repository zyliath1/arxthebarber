package com.example.application.views.palvelut;

import com.example.application.data.ServiceItem;
import com.example.application.services.ServiceItemService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.List;

@PageTitle("Palvelut")
@Route("palvelut")
@Menu(order = 2, icon = LineAwesomeIconUrl.CUT_SOLID)
@AnonymousAllowed
public class PalvelutView extends VerticalLayout {

    private final ServiceItemService service;

    public PalvelutView(ServiceItemService service) {
        this.service = service;
        setSpacing(true);
        setPadding(true);

        Grid<ServiceItem> grid = new Grid<>(ServiceItem.class, false);

        grid.addColumn(ServiceItem::getNimi).setHeader("Palvelun nimi").setAutoWidth(true);
        grid.addColumn(ServiceItem::getKuvaus).setHeader("Kuvaus").setAutoWidth(true);
        grid.addColumn(item -> String.format("%.2f €", item.getHinta())).setHeader("Hinta (€)").setAutoWidth(true);
        grid.addColumn(item -> item.getKestoMinuuteissa() + " min").setHeader("Kesto").setAutoWidth(true);

        List<ServiceItem> palvelut = service.findAll();
        grid.setItems(palvelut);

        add(grid);
        setSizeFull();
    }
}
