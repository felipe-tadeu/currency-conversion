package com.felipetadeu.currencyconversion.common.controlleradvice;

import com.felipetadeu.currencyconversion.common.model.dto.WebErrorDto;
import com.felipetadeu.currencyconversion.common.model.exception.InvalidParamException;
import com.felipetadeu.currencyconversion.common.model.exception.NoPathException;
import com.felipetadeu.currencyconversion.common.model.exception.UnexpectedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.money.UnknownCurrencyException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class WebRestControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            NoPathException.class,
            MissingServletRequestParameterException.class,
            UnknownCurrencyException.class,
            InvalidParamException.class
    })
    public WebErrorDto handleNoPathException(Exception e, HttpServletRequest h) {
        log.warn("#### -> handling error -> " + e);
        return new WebErrorDto(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage(), h.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({UnexpectedException.class, Exception.class})
    public WebErrorDto handleGeneralException(Exception e, HttpServletRequest h) {
        log.warn("#### -> handling error -> " + e);
        return new WebErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getLocalizedMessage(), h.getRequestURI());
    }
}
