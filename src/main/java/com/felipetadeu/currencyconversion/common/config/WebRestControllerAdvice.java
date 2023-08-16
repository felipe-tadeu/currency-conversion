package com.felipetadeu.currencyconversion.common.config;

import com.felipetadeu.currencyconversion.common.model.exception.NoPathException;
import com.felipetadeu.currencyconversion.common.model.dto.WebErrorDto;
import com.felipetadeu.currencyconversion.common.model.exception.UnexpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoPathException.class)
    public WebErrorDto handleNoPathException(NoPathException e, HttpServletRequest h) {
        return new WebErrorDto(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage(), h.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UnexpectedException.class)
    public WebErrorDto handleUnexpectedException(UnexpectedException e, HttpServletRequest h) {
        return new WebErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getLocalizedMessage(), h.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public WebErrorDto handleGeneralException(Exception e, HttpServletRequest h) {
        return new WebErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getLocalizedMessage(), h.getRequestURI());
    }
}
