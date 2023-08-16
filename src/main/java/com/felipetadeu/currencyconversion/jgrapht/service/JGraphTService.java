package com.felipetadeu.currencyconversion.jgrapht.service;

import lombok.Getter;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Getter
@Service
public class JGraphTService {

    private final DefaultDirectedWeightedGraph<String, BigDecimal> graph =
            new DefaultDirectedWeightedGraph<>(BigDecimal.class);

    public void addVertex(String currency) {
        this.graph.addVertex(currency);
    }

    public void addEdge(String inputCurrency, String outputCurrency, BigDecimal exchangeRate) {
        graph.addEdge(inputCurrency, outputCurrency, exchangeRate);
    }

}
