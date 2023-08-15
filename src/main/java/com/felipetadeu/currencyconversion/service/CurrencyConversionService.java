package com.felipetadeu.currencyconversion.service;

import com.felipetadeu.currencyconversion.model.Currency;

import java.math.BigDecimal;

public interface CurrencyConversionService {

    public BigDecimal convertCurrencyValueNative(Currency inputCurrency, Currency outputCurrency, BigDecimal amountToConvert);

}
