package common.events;

import org.fate7.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }

    public NewInventoryEvent() {
        super();
    }
}
