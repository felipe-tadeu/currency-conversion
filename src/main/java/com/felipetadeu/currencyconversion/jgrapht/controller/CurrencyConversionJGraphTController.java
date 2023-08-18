package com.felipetadeu.currencyconversion.jgrapht.controller;

import com.felipetadeu.currencyconversion.common.model.dto.ResponseDto;
import com.felipetadeu.currencyconversion.jgrapht.model.dto.ExchangeRatePairDto;
import com.felipetadeu.currencyconversion.jgrapht.service.CurrencyConversionJGraphTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Set;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/currency-conversion")
public class CurrencyConversionJGraphTController {

    private final CurrencyConversionJGraphTService service;

    @GetMapping("/jgrapht")
    public ResponseDto convertJGraphT(@Validated @RequestParam String input, @RequestParam String output, @RequestParam String exchange) {
        log.info("#### -> starting execution with params -> {input: {}, output: {}, exchange: {}}", input, output, exchange);
        var startExecution = System.currentTimeMillis();
        var returnAmount = this.service.convertCurrencyValue(input, output, exchange);
        var endExecution = System.currentTimeMillis();
        log.info("#### -> ending execution -> duration: {} milliseconds", endExecution - startExecution);
        return new ResponseDto(200, returnAmount.toString(), new Timestamp(endExecution));
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
