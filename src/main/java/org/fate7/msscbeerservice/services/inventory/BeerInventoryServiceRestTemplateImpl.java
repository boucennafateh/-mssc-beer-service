package org.fate7.msscbeerservice.services.inventory;

import lombok.extern.slf4j.Slf4j;
import org.fate7.msscbeerservice.services.inventory.model.BeerInventoryDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService{

    public static final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";

    private final Environment env;
    private final RestTemplate restTemplate;

    private final String beerInventoryServiceHost;

    public BeerInventoryServiceRestTemplateImpl(Environment env, RestTemplateBuilder restTemplateBuilder) {
        this.env = env;
        this.restTemplate = restTemplateBuilder.build();
        this.beerInventoryServiceHost = env.getProperty("inventory.host");
    }

    @Override
    public Integer getOnHandInventory(UUID id) {
        ResponseEntity<List<BeerInventoryDto>> listBeerInventory = restTemplate.exchange(
                beerInventoryServiceHost + INVENTORY_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BeerInventoryDto>>() {
                },
                id);
        int sum = Objects.requireNonNull(listBeerInventory.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
        return sum;
    }
}
