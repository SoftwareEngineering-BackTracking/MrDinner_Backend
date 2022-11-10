package com.backtracking.MrDinner.domain.demand.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderInfo {
    private List<Demand> demandList;
    private List<List<DemandItem>> demandItemList;
    private List<List<DemandDetail>> demandDetailList;
}
