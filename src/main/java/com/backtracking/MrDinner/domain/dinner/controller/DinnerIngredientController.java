package com.backtracking.MrDinner.domain.dinner.controller;

import com.backtracking.MrDinner.domain.dinner.dto.*;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientList;
import com.backtracking.MrDinner.domain.dinner.service.DinnerIngredientService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import com.backtracking.MrDinner.global.enumpackage.DinnerIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/dinneringredient")
public class DinnerIngredientController {
    private final DinnerIngredientService dinnerIngredientService;

    @PostMapping
    public ResponseEntity<DinnerIngredientCreateResponseDto> createDinnerIngredient(@RequestBody DinnerIngredientCreateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            dinnerIngredientService.createDinnerIngredient(requestDto);
            dtoMetaData = new DtoMetaData("디너 재고 생성 완료");
            return ResponseEntity.ok(new DinnerIngredientCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DinnerIngredientCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<DinnerIngredientFetchResponseDto> fetchDinnerIngredient(@RequestBody DinnerIngredientFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            if(requestDto.getDinnerIngredient() == null){
                List<DinnerIngredientList> dinnerIngredientList = dinnerIngredientService.fetchDinnerAllIngredient(requestDto);
                dtoMetaData = new DtoMetaData("디너 세부 재료 조회 완료");
                return ResponseEntity.ok(new DinnerIngredientFetchResponseDto(dtoMetaData, dinnerIngredientList));
            }
            else{
                List<DinnerIngredientList> dinnerIngredientList = dinnerIngredientService.fetchDinnerIngredient(requestDto);
                dtoMetaData = new DtoMetaData("전체 디너 재료 조회 완료");
                return ResponseEntity.ok(new DinnerIngredientFetchResponseDto(dtoMetaData, dinnerIngredientList));
            }

        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DinnerIngredientFetchResponseDto(dtoMetaData));
        }
    }


    @PutMapping
    public ResponseEntity<DinnerIngredientUpdateResponseDto> updateDinnerIngredient(@RequestBody DinnerIngredientUpdateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            dinnerIngredientService.updateDinnerIngredient(requestDto);
            dtoMetaData = new DtoMetaData("디너 재고 수정 완료");
            return ResponseEntity.ok(new DinnerIngredientUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DinnerIngredientUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<DinnerIngredientDeleteResponseDto> deleteDinnerIngredient(@RequestBody DinnerIngredientDeleteRequestDto requestDto){

        DtoMetaData dtoMetaData;

        try{
            dinnerIngredientService.deleteDinnerIngredient(requestDto);
            dtoMetaData = new DtoMetaData("디너 재고 삭제 완료");
            return ResponseEntity.ok(new DinnerIngredientDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DinnerIngredientDeleteResponseDto(dtoMetaData));
        }
    }
}
