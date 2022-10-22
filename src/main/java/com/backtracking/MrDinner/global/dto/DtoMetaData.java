package com.backtracking.MrDinner.global.dto;

import lombok.Data;

@Data
public class DtoMetaData {
    // response할 때 전달할 데이터 형식

    private String message;
    private String exception;

    public DtoMetaData(String message){
        this.message = message;
        this.exception = null;
    }
    public DtoMetaData(String message, String exception){
        this.message = message;
        this.exception = exception;
    }
}
