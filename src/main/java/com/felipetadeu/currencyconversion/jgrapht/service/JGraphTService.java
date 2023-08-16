package com.felipetadeu.currencyconversion.jgrapht.service;

import lombok.Getter;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;

@Getter
@Service
public class JGraphTService {

    private final DefaultDirectedWeightedGraph<CurrencyUnit, BigDecimal> graph =
            new DefaultDirectedWeightedGraph<>(BigDecimal.class);

    public void addVertex(CurrencyUnit currency) {
        this.graph.addVertex(currency);
    }

    public void addEdge(CurrencyUnit inputCurrency, CurrencyUnit outputCurrency, BigDecimal exchangeRate) {
        graph.addEdge(inputCurrency, outputCurrency, exchangeRate);
    }

}
