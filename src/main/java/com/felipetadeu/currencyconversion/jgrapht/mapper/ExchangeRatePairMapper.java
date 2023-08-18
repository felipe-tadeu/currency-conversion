package com.felipetadeu.currencyconversion.jgrapht.mapper;

import com.felipetadeu.currencyconversion.jgrapht.model.ExchangeRatePair;
import com.felipetadeu.currencyconversion.jgrapht.model.dto.ExchangeRatePairDto;
import org.mapstruct.Mapper;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ExchangeRatePairMapper {

    ExchangeRatePairDto toDto(ExchangeRatePair entity);

    Set<ExchangeRatePairDto> toDto(Set<ExchangeRatePair> entity);

    default String currencyUnitToString(CurrencyUnit currencyUnit) {
        return currencyUnit.getCurrencyCode();
    }

    default String bigDecimalToString(BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }

}
