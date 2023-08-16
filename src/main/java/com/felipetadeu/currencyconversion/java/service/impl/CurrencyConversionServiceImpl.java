package com.felipetadeu.currencyconversion.java.service.impl;

import com.felipetadeu.currencyconversion.common.model.Currency;
import com.felipetadeu.currencyconversion.java.model.CurrencyGraph;
import com.felipetadeu.currencyconversion.java.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    // TODO - finish implementation
    @Override
    public BigDecimal convertCurrencyValue(Currency inputCurrency,
                                                 Currency outputCurrency,
                                                 BigDecimal amountToConvert) {

        CurrencyGraph.addVertex(Currency.CAD, Currency.USD, new BigDecimal("0.76158"));
        CurrencyGraph.addVertex(Currency.USD, Currency.GBP, new BigDecimal("0.76700"));
        CurrencyGraph.addVertex(Currency.CHF, Currency.GBP, new BigDecimal("0.84295"));
        CurrencyGraph.addVertex(Currency.AUD, Currency.EUR, new BigDecimal("0.61175"));


        return CurrencyGraph.returnConvertedValue(inputCurrency, outputCurrency, amountToConvert);
    }

}
