package com.backtracking.MrDinner.domain.demand.dto;

import com.backtracking.MrDinner.domain.demand.repository.Demand;
import com.backtracking.MrDinner.domain.demand.repository.DemandDetail;
import com.backtracking.MrDinner.domain.demand.repository.DemandItem;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class DemandFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<Demand> demandList;
    private List<List<DemandItem>> demandItemList;
    private List<List<DemandDetail>> demandDetailList;

    public DemandFetchResponseDto(DtoMetaData dtoMetaData, List<Demand> demandList, List<List<DemandItem>> demandItemList, List<List<DemandDetail>> demandDetailList){
        this.dtoMetaData = dtoMetaData;
        this.demandList = demandList;
        this.demandItemList = demandItemList;
        this.demandDetailList = demandDetailList;
    }

    public DemandFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }

    public DemandFetchResponseDto(DtoMetaData dtoMetaData, List<Demand> demandList){
        this.dtoMetaData = dtoMetaData;
        this.demandList = demandList;
    }
}
