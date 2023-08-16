package com.felipetadeu.currencyconversion.java.model;

import com.felipetadeu.currencyconversion.common.model.Currency;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

// TODO - finish implementation
@Slf4j
public class CurrencyGraph {

    private final static Set<CurrencyEdge> graph = new HashSet<>();

    public static Set<CurrencyEdge> get() {
        return graph;
    }

//    public static void addVertex(Currency input, Currency output, BigDecimal exchange) {
//        var vertexToAdd = new CurrencyVertex(input, output, exchange);
//        if (!graph.contains(vertexToAdd)) {
//            graph.add(vertexToAdd);
//            graph.forEach(vertex -> addVertex(vertex.getOutput(), vertex.getInput(), exchange));
//        }
//    }
//
//    public static BigDecimal returnConvertedValue(Currency currencyToConvert, Currency currencyTarget, BigDecimal value) {
//
//
//        return null;
//    }

    public static void addVertex(Currency input, Currency output, BigDecimal exchange) {
        graph.add(new CurrencyEdge(input, output, exchange));
    }

    public static BigDecimal returnConvertedValue(Currency inputCurrency, Currency outputCurrency, BigDecimal amountToConvert) {

        var edgeInput = findEdge(inputCurrency, outputCurrency);
        if (Objects.nonNull(edgeInput))
            return amountToConvert.multiply(edgeInput.getExchange());

        var edgeOutput = findEdge(outputCurrency, inputCurrency);
        if (Objects.nonNull(edgeOutput))
            return amountToConvert.divide(edgeOutput.getExchange(), MathContext.DECIMAL128);

        return null;

    }

    private static List<CurrencyEdge> findAllEdgesInput(Currency input) {
        return graph.stream()
                .filter(vertex -> vertex.getInput().equals(input))
                .collect(Collectors.toList());
    }

    private static List<CurrencyEdge> findAllEdgesOutput(Currency output) {
        return graph.stream()
                .filter(vertex -> vertex.getOutput().equals(output))
                .collect(Collectors.toList());
    }

    private static CurrencyEdge findEdge(Currency input, Currency output) {
        return graph.stream()
                .filter(vertex -> vertex.getInput().equals(input) && vertex.getOutput().equals(output))
                .findFirst().orElse(null);
    }

}
