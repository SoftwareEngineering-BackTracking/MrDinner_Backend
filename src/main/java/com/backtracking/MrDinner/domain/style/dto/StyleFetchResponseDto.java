package com.backtracking.MrDinner.domain.style.dto;

import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.Data;

import java.util.List;

@Data
public class StyleFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<StyleList> styleList;

    public StyleFetchResponseDto(DtoMetaData dtoMetaData, List<StyleList> styleList){
        this.dtoMetaData = dtoMetaData;
        this.styleList = styleList;
    }

    public StyleFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
