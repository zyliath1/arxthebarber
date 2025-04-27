package com.example.application.data;

import com.example.application.services.BarberService;
import com.example.application.services.ServiceItemService;
import com.example.application.services.CustomerService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final ServiceItemService serviceItemService;
    private final BarberService barberService;
    private final CustomerService customerService;

    public DataInitializer(ServiceItemService serviceItemService, BarberService barberService, CustomerService customerService) {
        this.serviceItemService = serviceItemService;
        this.barberService = barberService;
        this.customerService = customerService;
    }
    @PostConstruct
    public void initData() {
        if (serviceItemService.findAll().isEmpty()) {
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

            System.out.println("✅ Palvelut alustettu tietokantaan");
        } else {
            System.out.println("ℹ️ Palvelut löytyivät jo — ei alusteta uudelleen");
        }

        if (barberService.findAll().isEmpty()) {
            Barber b1 = new Barber();
            b1.setNimi("Emma Executive");
            b1.setTitteli("Master Barber");
            b1.setKuvaus("Erikoistunut klassisiin leikkauksiin ja viimeistelyyn.");
            barberService.save(b1);

            Barber b2 = new Barber();
            b2.setNimi("Jere Junior");
            b2.setTitteli("Apulaisparturi");
            b2.setKuvaus("Hyvä parran muotoilussa ja asiakaspalvelussa.");
            barberService.save(b2);

            System.out.println("✅ Parturit alustettu tietokantaan");
        } else {
            System.out.println("ℹ️ Parturit löytyivät jo — ei alusteta uudelleen");
        }

        if (customerService.findAll().isEmpty()) {
            Customer john = new Customer();
            john.setUsername("user");
            john.setEtunimi("John");
            john.setSukunimi("Normal");
            john.setPuhelin("0401234567");
            john.setSahkoposti("john.normal@example.com");
            customerService.save(john);

            System.out.println("✅ Asiakas John Normal alustettu tietokantaan");
        } else {
            System.out.println("ℹ️ Asiakkaita löytyi jo — ei alusteta uudelleen");
        }
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
