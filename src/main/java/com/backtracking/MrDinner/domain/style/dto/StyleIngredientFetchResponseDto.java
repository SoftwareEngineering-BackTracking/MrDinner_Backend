package com.backtracking.MrDinner.domain.style.dto;

import com.backtracking.MrDinner.domain.style.repository.StyleIngredientList;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.Data;

import java.util.List;

@Data
public class StyleIngredientFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<StyleIngredientList> styleIngredientList;
    public StyleIngredientFetchResponseDto(DtoMetaData dtoMetaData, List<StyleIngredientList> styleIngredientList){
        this.dtoMetaData = dtoMetaData;
        this.styleIngredientList = styleIngredientList;
    }

    public StyleIngredientFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
