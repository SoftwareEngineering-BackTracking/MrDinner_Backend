package com.backtracking.MrDinner.domain.style.controller;

import com.backtracking.MrDinner.domain.style.dto.*;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientList;
import com.backtracking.MrDinner.domain.style.service.StyleIngredientService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/styleingredient")
@RequiredArgsConstructor
public class StyleIngredientController {

    private final StyleIngredientService styleIngredientService;

    @PostMapping
    public ResponseEntity<StyleIngredientCreateResponseDto> createStyleIngredient(@RequestBody StyleIngredientCreateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            styleIngredientService.createStyleIngredient(requestDto);
            dtoMetaData = new DtoMetaData("스타일 재고 생성 완료");
            return ResponseEntity.ok(new StyleIngredientCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StyleIngredientCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<StyleIngredientFetchResponseDto> fetchStyleIngredient(@RequestBody StyleIngredientFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            if(requestDto.getStyleIngredient() == null){
                List<StyleIngredientList> StyleIngredientList = styleIngredientService.fetchAllStyleIngredient(requestDto);
                dtoMetaData = new DtoMetaData("스타일 조회 완료");
                return ResponseEntity.ok(new StyleIngredientFetchResponseDto(dtoMetaData, StyleIngredientList));
            }
            else{
                List<StyleIngredientList> StyleIngredientList = styleIngredientService.fetchStyleIngredient(requestDto);
                dtoMetaData = new DtoMetaData("전체 스타일 조회 완료");
                return ResponseEntity.ok(new StyleIngredientFetchResponseDto(dtoMetaData, StyleIngredientList));
            }

        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StyleIngredientFetchResponseDto(dtoMetaData));
        }
    }


    @PutMapping
    public ResponseEntity<StyleIngredientUpdateResponseDto> updateStyleIngredient(@RequestBody StyleIngredientUpdateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            styleIngredientService.updateStyleIngredient(requestDto);
            dtoMetaData = new DtoMetaData("스타일 재고 수정 완료");
            return ResponseEntity.ok(new StyleIngredientUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StyleIngredientUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<StyleIngredientDeleteResponseDto> deleteStyleIngredient(@RequestBody StyleIngredientDeleteRequestDto requestDto){

        DtoMetaData dtoMetaData;

        try{
            styleIngredientService.deleteStyleIngredient(requestDto);
            dtoMetaData = new DtoMetaData("스타일 재고 삭제 완료");
            return ResponseEntity.ok(new StyleIngredientDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StyleIngredientDeleteResponseDto(dtoMetaData));
        }
    }
}
