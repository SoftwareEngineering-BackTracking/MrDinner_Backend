package com.backtracking.MrDinner.domain.style.controller;

import com.backtracking.MrDinner.domain.style.dto.StyleFetchRequestDto;
import com.backtracking.MrDinner.domain.style.dto.StyleFetchResponseDto;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
import com.backtracking.MrDinner.domain.style.service.StyleService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/style")
@RequiredArgsConstructor
public class StyleController {

    private final StyleService styleService;
    @GetMapping
    public ResponseEntity<StyleFetchResponseDto> fetchStyle(@RequestHeader Map<String, Object> params){
        DtoMetaData dtoMetaData;
        StyleFetchRequestDto requestDto = new StyleFetchRequestDto((Style) params.get("style"));
        try{
            if(requestDto.getStyle() == null){
                List<StyleList> StyleList = styleService.fetchAllStyle(requestDto);
                dtoMetaData = new DtoMetaData("전체 디너 조회 완료");
                return ResponseEntity.ok(new StyleFetchResponseDto(dtoMetaData, StyleList));
            }
            else{
                List<StyleList> StyleList = styleService.fetchStyle(requestDto);
                dtoMetaData = new DtoMetaData("디너 조회 완료");
                return ResponseEntity.ok(new StyleFetchResponseDto(dtoMetaData, StyleList));
            }
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StyleFetchResponseDto(dtoMetaData));
        }
    }
}
