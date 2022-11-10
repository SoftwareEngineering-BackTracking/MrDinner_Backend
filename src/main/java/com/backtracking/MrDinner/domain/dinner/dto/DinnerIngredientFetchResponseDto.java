package com.backtracking.MrDinner.domain.dinner.dto;

import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientList;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.Data;

import java.util.List;

@Data
public class DinnerIngredientFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<DinnerIngredientList> dinnerIngredientList;

    public DinnerIngredientFetchResponseDto(DtoMetaData dtoMetaData, List<DinnerIngredientList> dinnerIngredientList){
        this.dtoMetaData = dtoMetaData;
        this.dinnerIngredientList = dinnerIngredientList;
    }

    public DinnerIngredientFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }

}
