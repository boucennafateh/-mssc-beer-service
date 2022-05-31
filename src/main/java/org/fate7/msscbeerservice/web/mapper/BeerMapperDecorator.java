package org.fate7.msscbeerservice.web.mapper;

import org.brewery.model.BeerDto;
import org.fate7.msscbeerservice.Domain.Beer;
import org.fate7.msscbeerservice.services.inventory.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerMapper beerMapper;
    private BeerInventoryService beerInventoryService;

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Override
    public Beer toBeer(BeerDto beerDto) {
        return beerMapper.toBeer(beerDto);
    }

    @Override
    public BeerDto toBeerDto(Beer beer) {
        return beerMapper.toBeerDto(beer);
    }

    @Override
    public BeerDto toBeerDtoWithInventory(Beer beer) {
        BeerDto beerDto = beerMapper.toBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return beerDto;
    }
}
