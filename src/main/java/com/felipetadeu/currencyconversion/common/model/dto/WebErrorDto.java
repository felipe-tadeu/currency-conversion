package com.felipetadeu.currencyconversion.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class WebErrorDto {

    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    private int status;

    private String error;

    private String path;
}
