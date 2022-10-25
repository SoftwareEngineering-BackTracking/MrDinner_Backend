package com.backtracking.MrDinner.domain.demand.service;

import com.backtracking.MrDinner.domain.demand.dto.DemandCreateRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandDeleteRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandFetchRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandUpdateRequestDto;
import com.backtracking.MrDinner.domain.demand.repository.DemandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class DemandService {
    private final DemandRepository demandRepository;

    @Transactional
    public void createDemand(DemandCreateRequestDto requestDto, HttpSession session) {
    }

    @Transactional
    public void fetchDemand(DemandFetchRequestDto requestDto, HttpSession session) {
    }

    @Transactional
    public void updateDemand(DemandUpdateRequestDto requestDto, HttpSession session) {
    }

    @Transactional
    public void deleteDemand(DemandDeleteRequestDto requestDto) {
    }
}
