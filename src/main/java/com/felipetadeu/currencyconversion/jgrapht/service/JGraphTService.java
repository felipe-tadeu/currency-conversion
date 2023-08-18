package com.felipetadeu.currencyconversion.jgrapht.service;

import com.felipetadeu.currencyconversion.jgrapht.model.ExchangeRatePair;
import lombok.Getter;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Service
public class JGraphTService {

    private final DefaultDirectedWeightedGraph<CurrencyUnit, BigDecimal> graph =
            new DefaultDirectedWeightedGraph<>(BigDecimal.class);

    private final Set<ExchangeRatePair> exchangeRatePairs = new HashSet<>();

    public void addVertex(CurrencyUnit currency) {
        this.graph.addVertex(currency);
    }

    public void addEdge(CurrencyUnit inputCurrency, CurrencyUnit outputCurrency, BigDecimal exchangeRate) {

        var exchangeRatePair = new ExchangeRatePair(inputCurrency, outputCurrency, exchangeRate);

        exchangeRatePairs.add(exchangeRatePair);
        graph.addEdge(inputCurrency, outputCurrency, exchangeRate);
    }

    public Set<CurrencyUnit> getAllCurrencies() {
        return graph.vertexSet();
    }
}
