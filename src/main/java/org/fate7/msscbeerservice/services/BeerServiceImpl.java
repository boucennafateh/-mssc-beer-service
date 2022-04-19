package org.fate7.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fate7.msscbeerservice.Domain.Beer;
import org.fate7.msscbeerservice.repositories.BeerRepository;
import org.fate7.msscbeerservice.web.controller.NotFoundException;
import org.fate7.msscbeerservice.web.mapper.BeerMapper;
import org.fate7.msscbeerservice.web.model.BeerDto;
import org.fate7.msscbeerservice.web.model.BeerPagedList;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeerServiceImpl implements BeerService{

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
    public BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(() -> {
            throw new NotFoundException("Beer Id " + beerId + " not found");
        });
        if(showInventoryOnHand)
            return beerMapper.toBeerDtoWithInventory(beer);
        return beerMapper.toBeerDto(beer);
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        Beer beer = beerMapper.toBeer(beerDto);
        Beer beerSaved = beerRepository.save(beer);
        return beerMapper.toBeerDto(beerSaved);
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        // to check if beer does exist
        beerRepository.findById(beerId).orElseThrow(() -> {
            throw new NotFoundException("Beer Id " + beerId + " not found");
        });
        Beer beer = beerMapper.toBeer(beerDto);
        beer.setId(beerId);
        Beer beerUpdated = beerRepository.save(beer);
        return beerMapper.toBeerDto(beerUpdated);
    }

    @Override
    public void deleteBeer(UUID beerId) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(() -> {
            throw new NotFoundException("Beer Id " + beerId + " not found");
        });
        beerRepository.delete(beer);
    }

    @Override
    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    public BeerPagedList listBeers(String beerName, String beerStyle, PageRequest pageRequest,
                                   Boolean showInventoryOnHand) {

        log.info("calling the listBeers method");

        //BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if(StringUtils.hasText(beerName) && StringUtils.hasText(beerStyle))
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        else if(StringUtils.hasText(beerName) && !StringUtils.hasText(beerStyle))
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        else if(!StringUtils.hasText(beerName) && beerStyle != null)
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        else
            beerPage = beerRepository.findAll(pageRequest);

        if(showInventoryOnHand){
            return new BeerPagedList(
                    beerPage.getContent().stream()
                            .map(beerMapper::toBeerDtoWithInventory)
                            .collect(Collectors.toList()),
                    PageRequest.of(
                            beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements()
            );
        }
        else{
            return new BeerPagedList(
                    beerPage.getContent().stream()
                            .map(beerMapper::toBeerDto)
                            .collect(Collectors.toList()),
                    PageRequest.of(
                            beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements()
            );

        }





    }

    @Override
    @Cacheable(cacheNames = "beerUpcCache", key = "#upc", condition = "#showInventoryOnHand == false")
    public BeerDto getBeerByUpc(String upc, Boolean showInventoryOnHand) {
        Beer beer = beerRepository.findByUpc(upc).orElseThrow(() -> {
            throw new NotFoundException("Beer upc " + upc + " not found");
        });
        if(showInventoryOnHand)
            return beerMapper.toBeerDtoWithInventory(beer);
        return beerMapper.toBeerDto(beer);
    }
}
