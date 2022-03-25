package org.fate7.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import org.fate7.msscbeerservice.Domain.Beer;
import org.fate7.msscbeerservice.repositories.BeerRepository;
import org.fate7.msscbeerservice.web.controller.NotFoundException;
import org.fate7.msscbeerservice.web.mapper.BeerMapper;
import org.fate7.msscbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService{

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(() -> {
            throw new NotFoundException("Beer Id " + beerId + " not found");
        });
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
}
