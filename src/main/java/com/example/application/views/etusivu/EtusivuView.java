package com.example.application.views.etusivu;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Etusivu")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
@AnonymousAllowed
public class EtusivuView extends Composite<VerticalLayout> {

    public EtusivuView() {
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.setHeight("min-content");

        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");

        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");

        layoutColumn3.getStyle().set("flex-grow", "1");

        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.setHeight("min-content");

        // --- Sisältö alkaa ---
        H1 otsikko = new H1("Tervetuloa Barber studio Arx -palveluun!");
        Paragraph kuvaus = new Paragraph("Varaa aika, tutustu partureihin ja palveluihin helposti yhdestä paikasta.");
        Button varaaNappi = new Button("Varaa aika");
        RouterLink linkki = new RouterLink("", com.example.application.views.varaaaika.VaraaAikaView.class);
        linkki.add(varaaNappi);

        layoutColumn2.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutColumn2.add(otsikko, kuvaus, linkki);
        // --- Sisältö loppuu ---

        getContent().add(layoutRow2);
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutRow.add(layoutColumn3);
        getContent().add(layoutRow3);
    }
}
