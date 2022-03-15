package org.fate7.msscbeerservice.web.mapper;

import org.fate7.msscbeerservice.Domain.Beer;
import org.fate7.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    Beer toBeer(BeerDto beerDto);
    BeerDto toBeerDto(Beer beer);
}
