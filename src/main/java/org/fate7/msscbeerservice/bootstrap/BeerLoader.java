package org.fate7.msscbeerservice.bootstrap;

import lombok.RequiredArgsConstructor;
import org.fate7.msscbeerservice.Domain.Beer;
import org.fate7.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    public static final Long BEER_1_UPC = 631234200036l;
    public static final Long BEER_2_UPC = 631234300019l;
    public static final Long BEER_3_UPC = 83783375213l;

    private final BeerRepository beerRepository;



    @Override
    public void run(String... args) throws Exception {
        if(beerRepository.count() == 0)
            loadBeerObjects();
    }

    private void loadBeerObjects() {
        Beer b1 = Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle("IPA")
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_1_UPC)
                .build();

        Beer b2 = Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle("PALE_ALE")
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_2_UPC)
                .build();

        Beer b3 = Beer.builder()
                .beerName("Pinball Porter")
                .beerStyle("PALE_ALE")
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_3_UPC)
                .build();

        beerRepository.save(b1);
        beerRepository.save(b2);
        beerRepository.save(b3);

        System.out.println(beerRepository.count());
    }
}
