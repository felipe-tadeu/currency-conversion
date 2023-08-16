package com.felipetadeu.currencyconversion.java.model;

import com.felipetadeu.currencyconversion.common.model.Currency;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class CurrencyEdge implements Serializable {

    private Currency input;

    private Currency output;

    private BigDecimal exchange;

    @Override
    public String toString() {
        return "CurrencyVertex{" +
                "input=" + input +
                ", output=" + output +
                ", exchange=" + exchange +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyEdge that = (CurrencyEdge) o;
        return input == that.input && output == that.output;
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, output);
    }
}
