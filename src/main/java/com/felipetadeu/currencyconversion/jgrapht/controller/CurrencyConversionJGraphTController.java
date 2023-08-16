package com.felipetadeu.currencyconversion.jgrapht.controller;

import com.felipetadeu.currencyconversion.common.model.dto.ResponseDto;
import com.felipetadeu.currencyconversion.jgrapht.service.CurrencyConversionJGraphTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@Slf4j
@RestController
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

}
