package com.herringbone.ibdanalyzer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection="ibdStocks")
@TypeAlias("ibdStock")
public class IBDStock {

    @Id
    private String symbol;
    @Builder.Default
    private List<Double> prices = new ArrayList<>();
    @Builder.Default
    private List<Double> priceChgs = new ArrayList<>();
    @Builder.Default
    private List<Double> pricePctChgs = new ArrayList<>();
    @Builder.Default
    private List<Integer> compRatings = new ArrayList<>();
    @Builder.Default
    private List<Integer> rsRatings = new ArrayList<>();
    @Builder.Default
    private List<LocalDate> screenDates = new ArrayList<>();
    @Builder.Default
    private List<Integer> volumes = new ArrayList<>();
    @Builder.Default
    private List<Integer> volumePctChgs = new ArrayList<>();

    @Override
    public String toString() {
        return String.format(
                "Customer[symbol=%s, prices='%s', rsRatings='%s']",
                symbol, prices, rsRatings);
    }
}
