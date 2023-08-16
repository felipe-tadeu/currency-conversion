package com.felipetadeu.currencyconversion.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private int status;

    private String body;

    private final Timestamp timestamp;

}
