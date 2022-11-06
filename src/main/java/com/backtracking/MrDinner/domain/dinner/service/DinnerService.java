package com.backtracking.MrDinner.domain.dinner.service;

import com.backtracking.MrDinner.domain.dinner.dto.DinnerFetchRequestDto;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DinnerService {

    private final DinnerRepository dinnerRepository;

    @Transactional
    public List<DinnerList> fetchDinner(DinnerFetchRequestDto requestDto) {
        DinnerList dinner = dinnerRepository.findById(requestDto.getDinner()).orElseThrow(() -> new IllegalArgumentException("해당 디너 메뉴가 없습니다."));
        List<DinnerList> dinnerLists = new ArrayList<>();
        dinnerLists.add(dinner);

        return dinnerLists;
    }

    @Transactional
    public List<DinnerList> fetchAllDinner(DinnerFetchRequestDto requestDto) {
        List<DinnerList> dinnerLists = dinnerRepository.findAll();

        if(dinnerLists.isEmpty()){
            throw new IllegalArgumentException("디너 메뉴가 없습니다.");
        }
        else{
            return dinnerLists;
        }
    }
}
