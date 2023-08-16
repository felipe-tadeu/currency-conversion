package com.felipetadeu.currencyconversion.jgrapht.controller;

import com.felipetadeu.currencyconversion.jgrapht.service.CurrencyConversionJGraphTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency-conversion")
public class CurrencyConversionJGraphTController {

    private final CurrencyConversionJGraphTService service;

    @GetMapping("/jgrapht")
    public BigDecimal convertJGraphT(@RequestParam String input, @RequestParam String output, @RequestParam String exchange) {
        return this.service.convertCurrencyValue(input, output, new BigDecimal(exchange));
    }

}
