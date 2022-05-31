package org.fate7.msscbeerservice.web.mapper;

import org.brewery.model.BeerDto;
import org.fate7.msscbeerservice.Domain.Beer;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer toBeer(BeerDto beerDto);
    BeerDto toBeerDto(Beer beer);
    BeerDto toBeerDtoWithInventory(Beer beer);
}
