package com.backtracking.MrDinner.domain.style.controller;

import com.backtracking.MrDinner.domain.style.dto.StyleIngredientFetchRequestDto;
import com.backtracking.MrDinner.domain.style.dto.StyleIngredientFetchResponseDto;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientList;
import com.backtracking.MrDinner.domain.style.service.StyleIngredientService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/styleingredient")
@RequiredArgsConstructor
public class StyleIngredientController {

    private final StyleIngredientService styleIngredientService;
    @GetMapping
    public ResponseEntity<StyleIngredientFetchResponseDto> fetchStyleIngredient(@RequestBody StyleIngredientFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{

            List<StyleIngredientList> StyleIngredientList = styleIngredientService.fetchAllStyleIngredient(requestDto);
            dtoMetaData = new DtoMetaData("전체 디너 조회 완료");
            return ResponseEntity.ok(new StyleIngredientFetchResponseDto(dtoMetaData, StyleIngredientList));

        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StyleIngredientFetchResponseDto(dtoMetaData));
        }
    }
}
