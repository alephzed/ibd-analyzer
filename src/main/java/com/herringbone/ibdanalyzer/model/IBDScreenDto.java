package com.herringbone.ibdanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IBDScreenDto {
    private List<IBDStockDto> stocks;
    private LocalDate screenDate;
    private String screenType;
}
