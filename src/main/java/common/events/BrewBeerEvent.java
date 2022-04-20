package common.events;

import org.fate7.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }

    public BrewBeerEvent() {
        super();
    }
}
