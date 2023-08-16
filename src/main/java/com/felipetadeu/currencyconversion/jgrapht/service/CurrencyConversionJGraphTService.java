package com.felipetadeu.currencyconversion.jgrapht.service;

import com.felipetadeu.currencyconversion.common.model.exception.NoPathException;
import com.felipetadeu.currencyconversion.common.model.exception.UnexpectedException;
import com.felipetadeu.currencyconversion.common.util.CurrencyUtil;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
public class CurrencyConversionJGraphTService {

    private final JGraphTService jGraphTService;

    private CurrencyConversionJGraphTService(JGraphTService jGraphTService) {

        this.jGraphTService = jGraphTService;

        loadDataForTests();
    }

    private void loadDataForTests() {

        log.info("#### -> loading currencies");

        var currencyCAD = CurrencyUtil.getCurrencyFromString("CAD");
        var currencyUSD = CurrencyUtil.getCurrencyFromString("USD");
        var currencyGBP = CurrencyUtil.getCurrencyFromString("GBP");
        var currencyCHF = CurrencyUtil.getCurrencyFromString("CHF");
        var currencyAUD = CurrencyUtil.getCurrencyFromString("AUD");
        var currencyEUR = CurrencyUtil.getCurrencyFromString("EUR");

        log.info("#### -> loading vertices");

        jGraphTService.addVertex(currencyCAD);
        jGraphTService.addVertex(currencyUSD);
        jGraphTService.addVertex(currencyGBP);
        jGraphTService.addVertex(currencyCHF);
        jGraphTService.addVertex(currencyAUD);
        jGraphTService.addVertex(currencyEUR);

        log.info("#### -> loading edges");

        jGraphTService.addEdge(currencyCAD, currencyUSD, new BigDecimal("0.76158"));
        jGraphTService.addEdge(currencyUSD, currencyGBP, new BigDecimal("0.76700"));
        jGraphTService.addEdge(currencyCHF, currencyGBP, new BigDecimal("0.84295"));
        jGraphTService.addEdge(currencyAUD, currencyEUR, new BigDecimal("0.61175"));
    }

    public BigDecimal convertCurrencyValue(String inputCurrencyString,
                                           String outputCurrencyString,
                                           String amountToConvertString) {

        var inputCurrency = CurrencyUtil.getCurrencyFromString(inputCurrencyString);
        var outputCurrency = CurrencyUtil.getCurrencyFromString(outputCurrencyString);
        var amountToConvert = CurrencyUtil.getAmountFromString(amountToConvertString);

        log.info("#### -> using Dijkstra algorithm");

        var dijkstraPath = new DijkstraShortestPath<>(jGraphTService.getGraph());
        var path = dijkstraPath.getPath(inputCurrency, outputCurrency);

        log.info("#### -> verifying path");

        if (Objects.isNull(path))
            throw new NoPathException("There is no path to execute this conversion");

        log.info("#### -> calculating exchange");

        var exchangeValue = path.getEdgeList()
                .stream()
                .reduce(BigDecimal::multiply)
                .orElseThrow(() ->
                        new UnexpectedException("An unexpected error has occurred: no value has returned from the multiplying of the exchange values"));

        log.info("#### -> calculating the final amount");

        return amountToConvert.multiply(exchangeValue);
    }
}
