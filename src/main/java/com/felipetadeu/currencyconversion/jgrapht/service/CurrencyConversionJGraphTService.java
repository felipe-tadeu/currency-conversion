package com.felipetadeu.currencyconversion.jgrapht.service;

import com.felipetadeu.currencyconversion.common.mapper.ExchangeRatePairMapper;
import com.felipetadeu.currencyconversion.common.model.dto.ExchangeRatePairDto;
import com.felipetadeu.currencyconversion.common.model.exception.NoPathException;
import com.felipetadeu.currencyconversion.common.model.exception.UnexpectedException;
import com.felipetadeu.currencyconversion.common.util.CurrencyUtil;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CurrencyConversionJGraphTService {

    private final JGraphTService jGraphTService;

    private final ExchangeRatePairMapper exchangeRatePairMapper;

    private CurrencyConversionJGraphTService(JGraphTService jGraphTService,
                                             ExchangeRatePairMapper exchangeRatePairMapper) {

        this.jGraphTService = jGraphTService;
        this.exchangeRatePairMapper = exchangeRatePairMapper;

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

        return amountToConvert.multiply(exchangeValue).setScale(5, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public Set<String> getAllCurrencies() {
        return jGraphTService.getAllCurrencies().stream().map(CurrencyUnit::getCurrencyCode).collect(Collectors.toSet());
    }

    public Set<ExchangeRatePairDto> getAllExchangeRatePairs() {
        return exchangeRatePairMapper.toDto(this.jGraphTService.getExchangeRatePairs());
    }
}
