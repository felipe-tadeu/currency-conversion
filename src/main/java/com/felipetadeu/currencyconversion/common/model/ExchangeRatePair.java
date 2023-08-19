package com.felipetadeu.currencyconversion.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExchangeRatePair {

    private CurrencyUnit inputCurrency;

    private CurrencyUnit outputCurrency;

    private BigDecimal exchangeRate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeRatePair that = (ExchangeRatePair) o;

        if (!inputCurrency.equals(that.inputCurrency)) return false;
        return outputCurrency.equals(that.outputCurrency);
    }

    @Override
    public int hashCode() {
        int result = inputCurrency.hashCode();
        result = 31 * result + outputCurrency.hashCode();
        return result;
    }
}
