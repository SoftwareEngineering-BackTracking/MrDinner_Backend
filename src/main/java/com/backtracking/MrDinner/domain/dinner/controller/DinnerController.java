package com.backtracking.MrDinner.domain.dinner.controller;

import com.backtracking.MrDinner.domain.dinner.dto.DinnerFetchRequestDto;
import com.backtracking.MrDinner.domain.dinner.dto.DinnerFetchResponseDto;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.domain.dinner.service.DinnerService;
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
@RequestMapping("/api/dinner")
public class DinnerController {
    private final DinnerService dinnerService;
    @GetMapping
    public ResponseEntity<DinnerFetchResponseDto> fetchDinner(@RequestBody DinnerFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            if(requestDto.getDinner() == null){
                List<DinnerList> dinnerList = dinnerService.fetchAllDinner(requestDto);
                dtoMetaData = new DtoMetaData("전체 디너 조회 완료");
                return ResponseEntity.ok(new DinnerFetchResponseDto(dtoMetaData, dinnerList));
            }
            else{
                List<DinnerList> dinnerList = dinnerService.fetchDinner(requestDto);
                dtoMetaData = new DtoMetaData("디너 조회 완료");
                return ResponseEntity.ok(new DinnerFetchResponseDto(dtoMetaData, dinnerList));
            }
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DinnerFetchResponseDto(dtoMetaData));
        }
    }
}
