package org.fate7.msscbeerservice.web.mapper;

public abstract class BeerMapperDecorator implements BeerMapper {
    /*private BeerMapper beerMapper;
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
        BeerDto beerDto = beerMapper.toBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return beerDto;
    }*/
}
