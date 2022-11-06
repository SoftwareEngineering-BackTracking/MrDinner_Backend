package com.backtracking.MrDinner.domain.dinner.controller;

import com.backtracking.MrDinner.domain.dinner.dto.DinnerIngredientFetchRequestDto;
import com.backtracking.MrDinner.domain.dinner.dto.DinnerIngredientFetchResponseDto;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientList;
import com.backtracking.MrDinner.domain.dinner.service.DinnerIngredientService;
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
@RequiredArgsConstructor
@RequestMapping("/api/dinneringredient")
public class DinnerIngredientController {
    private final DinnerIngredientService dinnerIngredientService;
    @GetMapping
    public ResponseEntity<DinnerIngredientFetchResponseDto> fetchDinnerIngredient(@RequestBody DinnerIngredientFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            List<DinnerIngredientList> dinnerIngredientList = dinnerIngredientService.fetchDinnerAllIngredient(requestDto);
            dtoMetaData = new DtoMetaData("디너 세부 재료 조회 완료");
            return ResponseEntity.ok(new DinnerIngredientFetchResponseDto(dtoMetaData, dinnerIngredientList));

        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DinnerIngredientFetchResponseDto(dtoMetaData));
        }
    }
}
