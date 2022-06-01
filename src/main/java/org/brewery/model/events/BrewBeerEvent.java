package org.brewery.model.events;

import org.brewery.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }

    public BrewBeerEvent() {
        super();
    }
}
