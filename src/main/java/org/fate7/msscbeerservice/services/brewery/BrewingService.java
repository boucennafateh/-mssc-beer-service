package org.fate7.msscbeerservice.services.brewery;

import common.events.BrewBeerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fate7.msscbeerservice.Domain.Beer;
import org.fate7.msscbeerservice.config.JmsConfig;
import org.fate7.msscbeerservice.repositories.BeerRepository;
import org.fate7.msscbeerservice.services.inventory.BeerInventoryService;
import org.fate7.msscbeerservice.web.mapper.BeerMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beerList = beerRepository.findAll();
        beerList.forEach(beer -> {
            Integer onHandInventory = beerInventoryService.getOnHandInventory(beer.getId());
            Integer minOnHand = beer.getMinOnHand();
            log.debug("min on hand = " + minOnHand);
            log.debug("inventory on hand = " + onHandInventory);
            if(minOnHand >= onHandInventory)
                jmsTemplate.convertAndSend(JmsConfig.BREW_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.toBeerDto(beer)));
        });
    }
}
