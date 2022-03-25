package org.fate7.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

    @Null
    private UUID id;

    @NotBlank
    private String beerName;

    @NotNull
    private BeerStyle beerStyle;

    @NotNull
    private String upc;

    private Integer quantityOnHand;

    @NotNull
    @Positive
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;

    @Null
    private Integer version;

    @Null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private OffsetDateTime createdDate;

    @Null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private OffsetDateTime lastModifiedDate;

}
