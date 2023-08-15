package com.felipetadeu.currencyconversion.service.impl;

import com.felipetadeu.currencyconversion.model.Currency;
import com.felipetadeu.currencyconversion.model.CurrencyVertex;
import com.felipetadeu.currencyconversion.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    private final List<CurrencyVertex> currencyVertices;

    public CurrencyConversionServiceImpl() {
        this.currencyVertices = new ArrayList<>();
        this.currencyVertices.add(new CurrencyVertex(Currency.CAD, Currency.USD, new BigDecimal("0.76158")));
        this.currencyVertices.add(new CurrencyVertex(Currency.USD, Currency.GBP, new BigDecimal("0.76700")));
        this.currencyVertices.add(new CurrencyVertex(Currency.CHF, Currency.GBP, new BigDecimal("0.84295")));
        this.currencyVertices.add(new CurrencyVertex(Currency.AUD, Currency.EUR, new BigDecimal("0.61175")));
    }

    @Override
    public BigDecimal convertCurrencyValueNative(Currency inputCurrency,
                                                 Currency outputCurrency,
                                                 BigDecimal amountToConvert) {
        

        return null;
    }

}
