package com.backtracking.MrDinner.domain.demand.controller;

import com.backtracking.MrDinner.domain.demand.dto.*;
import com.backtracking.MrDinner.domain.demand.repository.Demand;
import com.backtracking.MrDinner.domain.demand.repository.OrderInfo;
import com.backtracking.MrDinner.domain.demand.service.DemandService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/demand")
public class DemandController {
    private final DemandService demandService;

    @PostMapping
    public ResponseEntity<DemandCreateResponseDto> createDemand(@RequestBody DemandCreateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            demandService.createDemand(requestDto);
            dtoMetaData = new DtoMetaData("주문 생성 완료");
            return ResponseEntity.ok(new DemandCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DemandCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<DemandFetchResponseDto> fetchDemand(@RequestHeader Map<String, String> params){
        DtoMetaData dtoMetaData;
        DemandFetchRequestDto requestDto = new DemandFetchRequestDto(params.get("filter"));
        try{
            if(requestDto.getFilter() == null){
                OrderInfo demandList = demandService.fetchAllDemand(requestDto);
                dtoMetaData = new DtoMetaData("전체 주문 조회 완료");
                return ResponseEntity.ok(new DemandFetchResponseDto(dtoMetaData, demandList.getDemandList(), demandList.getDemandItemList(), demandList.getDemandDetailList()));
            }
            else {
                OrderInfo orderInfo = demandService.fetchDemand(requestDto);
                dtoMetaData = new DtoMetaData("주문 조회 완료");
                return ResponseEntity.ok(new DemandFetchResponseDto(dtoMetaData, orderInfo.getDemandList(), orderInfo.getDemandItemList(), orderInfo.getDemandDetailList()));
            }
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DemandFetchResponseDto(dtoMetaData));
        }
    }

    @PutMapping
    public ResponseEntity<DemandUpdateResponseDto> updateDemand(@RequestBody DemandUpdateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            demandService.updateDemand(requestDto);
            dtoMetaData = new DtoMetaData("주문 수정 완료");
            return ResponseEntity.ok(new DemandUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DemandUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<DemandCancelResponseDto> cancelDemand(@RequestBody DemandCancelRequestDto requestDto){

        DtoMetaData dtoMetaData;

        try{
            demandService.cancelDemand(requestDto);
            dtoMetaData = new DtoMetaData("주문 삭제 완료");
            return ResponseEntity.ok(new DemandCancelResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DemandCancelResponseDto(dtoMetaData));
        }
    }
}
