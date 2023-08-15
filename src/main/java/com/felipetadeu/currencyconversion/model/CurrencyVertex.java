package com.felipetadeu.currencyconversion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CurrencyVertex {

    private Currency input;

    private Currency output;

    private BigDecimal exchange;

}
