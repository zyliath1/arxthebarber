package com.example.application.views.parturit;

import com.example.application.data.Barber;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

public class ParturitViewCard extends ListItem {

    public ParturitViewCard(Barber barber) {
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN,
                AlignItems.START, Padding.MEDIUM, BorderRadius.LARGE);

        Span nimi = new Span(barber.getNimi());
        nimi.addClassNames(FontSize.XLARGE, FontWeight.BOLD);

        Span titteli = new Span(barber.getTitteli());
        titteli.addClassNames(FontSize.MEDIUM, TextColor.SECONDARY);

        Paragraph kuvaus = new Paragraph(barber.getKuvaus());
        kuvaus.addClassName(Margin.Vertical.MEDIUM);

        add(nimi, titteli, kuvaus);
    }
}
