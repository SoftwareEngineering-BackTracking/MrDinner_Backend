package com.backtracking.MrDinner.domain.demand.dto;

import com.backtracking.MrDinner.domain.demand.repository.DemandDetail;
import com.backtracking.MrDinner.domain.demand.repository.DemandItem;
import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Data;

import java.util.List;

@Data
public class DemandUpdateRequestDto {
    private Long demandNo;
    private List<DemandItem> demandItemList;
    private List<DemandDetail> demandDetailList;
}
