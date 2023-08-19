package com.felipetadeu.currencyconversion.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRatePairDto {
    private String inputCurrency;
    private String outputCurrency;
    private String exchangeRate;
}
