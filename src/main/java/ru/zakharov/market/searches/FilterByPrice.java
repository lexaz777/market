package ru.zakharov.market.searches;

import lombok.Data;

@Data
public class FilterByPrice {
    private int minPrice = 0;
    private int maxPrice = 0;
}
