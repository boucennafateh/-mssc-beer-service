package org.fate7.msscbeerservice.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.fate7.msscbeerservice.web.model.BeerDto;

import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 6216352916345956755L;

    private final BeerDto beerDto;
}
