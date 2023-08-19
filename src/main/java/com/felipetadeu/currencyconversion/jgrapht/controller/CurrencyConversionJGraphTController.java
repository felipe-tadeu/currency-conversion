package com.felipetadeu.currencyconversion.jgrapht.controller;

import com.felipetadeu.currencyconversion.common.model.dto.ResponseDto;
import com.felipetadeu.currencyconversion.common.model.dto.ExchangeRatePairDto;
import com.felipetadeu.currencyconversion.jgrapht.service.CurrencyConversionJGraphTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/currency-conversion")
public class CurrencyConversionJGraphTController {

    private final CurrencyConversionJGraphTService service;

    @GetMapping("/jgrapht")
    public ResponseDto convertJGraphT(@RequestParam String inputCurrency,
                                      @RequestParam String outputCurrency,
                                      @RequestParam String amountToConvert) {
        log.info("#### -> starting execution with params -> {inputCurrency: {}, outputCurrency: {}, amountToConvert: {}}",
                inputCurrency, outputCurrency, amountToConvert);
        var startExecution = System.currentTimeMillis();
        var convertedValue = this.service.convertCurrencyValue(inputCurrency, outputCurrency, amountToConvert);
        var response = new ResponseDto(convertedValue.toString());
        var endExecution = System.currentTimeMillis();
        log.info("#### -> ending execution -> duration: {} milliseconds", endExecution - startExecution);
        return response;
    }

    @GetMapping("/jgrapht/currencies")
    public Set<String> getAllCurrencies() {
        log.info("#### -> starting execution");
        var startExecution = System.currentTimeMillis();
        var listCurrencies = this.service.getAllCurrencies();
        var endExecution = System.currentTimeMillis();
        log.info("#### -> ending execution -> duration: {} milliseconds", endExecution - startExecution);
        return listCurrencies;
    }

    @GetMapping("/jgrapht/exchange-rate-pairs")
    public Set<ExchangeRatePairDto> getAllExchangeRatePairs() {
        log.info("#### -> starting execution");
        var startExecution = System.currentTimeMillis();
        var listExchangeRatePair = this.service.getAllExchangeRatePairs();
        var endExecution = System.currentTimeMillis();
        log.info("#### -> ending execution -> duration: {} milliseconds", endExecution - startExecution);
        return listExchangeRatePair;
    }
}
