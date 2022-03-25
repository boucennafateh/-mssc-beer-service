package org.fate7.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fate7.msscbeerservice.bootstrap.BeerLoader;
import org.fate7.msscbeerservice.services.BeerService;
import org.fate7.msscbeerservice.web.model.BeerDto;
import org.fate7.msscbeerservice.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        given(beerService.getBeerById(any())).willReturn(getValidDto());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveBeer() throws Exception {
        BeerDto beerDto = getValidDto();
        String beerDtoString = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveBeer(any())).willReturn(getValidDto());

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoString))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto beerDto = getValidDto();
        String beerDtoString = objectMapper.writeValueAsString(beerDto);

        given(beerService.updateBeer(any(), any())).willReturn(getValidDto());

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoString))
                .andExpect(status().isNoContent());

    }

    BeerDto getValidDto(){

        return BeerDto.builder()
                .beerName("beer")
                .beerStyle(BeerStyle.ALE)
                .upc(BeerLoader.BEER_1_UPC)
                .price(BigDecimal.valueOf(1))
                .build();
    }
}