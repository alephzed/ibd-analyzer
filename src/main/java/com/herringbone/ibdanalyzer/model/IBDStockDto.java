package com.herringbone.ibdanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IBDStockDto {
    private String symbol;
    private Double price;
    private Double priceChg;
    private Double pricePctChg;
    private Integer compRating;
    private Integer rsRating;
    private LocalDate screenDate;
    private Integer volume;
    private Integer volumePctChg;
}
