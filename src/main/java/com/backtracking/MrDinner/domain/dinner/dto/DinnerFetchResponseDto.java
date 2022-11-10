package com.backtracking.MrDinner.domain.dinner.dto;

import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.Data;

import java.util.List;

@Data
public class DinnerFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<DinnerList> dinnerList;

    public DinnerFetchResponseDto(DtoMetaData dtoMetaData, List<DinnerList> dinnerList){
        this.dtoMetaData = dtoMetaData;
        this.dinnerList = dinnerList;
    }

    public DinnerFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }

}
