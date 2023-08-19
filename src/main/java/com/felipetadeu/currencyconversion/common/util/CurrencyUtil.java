package com.felipetadeu.currencyconversion.common.util;

import com.felipetadeu.currencyconversion.common.model.exception.InvalidParamException;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyUtil {

    public static CurrencyUnit getCurrencyFromString(String currencyString) {

        if (currencyString.isBlank())
            throw new InvalidParamException("Param must not be blank");

        if (currencyString.contains(","))
            throw new InvalidParamException(
                    String.format("Invalid param: %s. You may have passed a param more than once", currencyString));

        return Monetary.getCurrency(currencyString);
    }

    public static BigDecimal getAmountFromString(String amountString) {

        if (amountString.isBlank())
            throw new InvalidParamException("Param must not be blank");

        if (amountString.contains(","))
            throw new InvalidParamException(
                    String.format("Invalid param: %s. You may have passed a param more than once", amountString));

        return new BigDecimal(amountString).setScale(5, RoundingMode.HALF_UP).stripTrailingZeros();
    }
}
