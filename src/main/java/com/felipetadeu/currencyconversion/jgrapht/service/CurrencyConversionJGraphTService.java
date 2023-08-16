package com.felipetadeu.currencyconversion.jgrapht.service;

import com.felipetadeu.currencyconversion.common.model.Currency;
import com.felipetadeu.currencyconversion.common.model.exception.NoPathException;
import com.felipetadeu.currencyconversion.common.model.exception.UnexpectedException;
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

        log.info("#### -> loading vertices");

        this.jGraphTService.addVertex(Currency.CAD.name());
        this.jGraphTService.addVertex(Currency.USD.name());
        this.jGraphTService.addVertex(Currency.GBP.name());
        this.jGraphTService.addVertex(Currency.CHF.name());
        this.jGraphTService.addVertex(Currency.AUD.name());
        this.jGraphTService.addVertex(Currency.EUR.name());

        log.info("#### -> loading edges");

        this.jGraphTService.addEdge(Currency.CAD.name(), Currency.USD.name(), new BigDecimal("0.76158"));
        this.jGraphTService.addEdge(Currency.USD.name(), Currency.GBP.name(), new BigDecimal("0.76700"));
        this.jGraphTService.addEdge(Currency.CHF.name(), Currency.GBP.name(), new BigDecimal("0.84295"));
        this.jGraphTService.addEdge(Currency.AUD.name(), Currency.EUR.name(), new BigDecimal("0.61175"));
    }

    public BigDecimal convertCurrencyValue(String inputCurrency,
                                           String outputCurrency,
                                           BigDecimal amountToConvert) {

        log.info("#### -> loading Dijkstra algorithm");

        var dijkstraPath = new DijkstraShortestPath<>(this.jGraphTService.getGraph());
        var path = dijkstraPath.getPath(inputCurrency, outputCurrency);

        log.info("#### -> verifying path");

        if (Objects.isNull(path))
            throw new NoPathException("There is no path to execute this conversion.");

        log.info("#### -> calculating exchange");

        var exchangeValue = path.getEdgeList()
                .stream()
                .reduce(BigDecimal::multiply)
                .orElseThrow(() ->
                        new UnexpectedException("An unexpected error has occurred: no value has returned from the multiplying of the exchange values."));

        log.info("#### -> calculating the final amount");

        return amountToConvert.multiply(exchangeValue);
    }
}
