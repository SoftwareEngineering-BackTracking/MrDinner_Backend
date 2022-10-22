package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class IdRecoveryResponseDto {
    private DtoMetaData dtoMetaData;
    private String id;

    public IdRecoveryResponseDto(DtoMetaData dtoMetaData, String id){
        this.dtoMetaData = dtoMetaData;
        this.id = id;
    }

    public IdRecoveryResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
