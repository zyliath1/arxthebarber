package com.example.application.data;

import com.example.application.services.ServiceItemService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final ServiceItemService serviceItemService;

    public DataInitializer(ServiceItemService serviceItemService) {
        this.serviceItemService = serviceItemService;
    }

    @PostConstruct
    public void initData() {
        serviceItemService.save(createServiceItem("Hiustenleikkaus",
                "Klassinen hiustenleikkaus sisältäen pesun ja viimeistelyn. Sopii lyhyille tai keskipitkille hiuksille.",
                45.00, 30));

        serviceItemService.save(createServiceItem("Pitkien hiusten leikkaus",
                "Huolellinen leikkaus pitkille hiuksille sisältäen konsultoinnin, pesun ja viimeistelyn.",
                55.00, 45));

        serviceItemService.save(createServiceItem("Parran muotoilu",
                "Parran siistiminen ja muotoilu asiakkaan toiveiden mukaisesti. Sisältää rajaukset ja hoitotuotteet.",
                40.00, 25));

        serviceItemService.save(createServiceItem("Parranajo",
                "Perinteinen märkäajo partasudilla, höylällä ja kuumilla pyyhkeillä.",
                43.00, 30));

        serviceItemService.save(createServiceItem("Kevyt parran siistintä",
                "Pikaistunta parran ja viiksien nopeaan siistimiseen ilman muotoilua.",
                25.00, 15));

        serviceItemService.save(createServiceItem("Lasten hiustenleikkaus (alle 10 v)",
                "Leikkaus lapsille rennossa ilmapiirissä. Vanhemmat voivat olla mukana koko ajan.",
                38.00, 25));

        serviceItemService.save(createServiceItem("Isä & poika -leikkaus",
                "Samanaikainen hiustenleikkaus isälle ja pojalle – aikaa yhdessä ja tyylikäs lopputulos molemmille.",
                76.00, 45));
    }

    private ServiceItem createServiceItem(String nimi, String kuvaus, Double hinta, Integer kestoMinuuteissa) {
        ServiceItem item = new ServiceItem();
        item.setNimi(nimi);
        item.setKuvaus(kuvaus);
        item.setHinta(hinta);
        item.setKestoMinuuteissa(kestoMinuuteissa);
        return item;
    }
}
