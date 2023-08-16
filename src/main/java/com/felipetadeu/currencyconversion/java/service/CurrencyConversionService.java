package com.felipetadeu.currencyconversion.java.service;

import com.felipetadeu.currencyconversion.common.model.Currency;

import java.math.BigDecimal;

public interface CurrencyConversionService {

    public BigDecimal convertCurrencyValue(Currency inputCurrency, Currency outputCurrency, BigDecimal amountToConvert);

}
