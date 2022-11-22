package com.backtracking.MrDinner.domain.dinner.service;

import com.backtracking.MrDinner.domain.dinner.dto.DinnerIngredientCreateRequestDto;
import com.backtracking.MrDinner.domain.dinner.dto.DinnerIngredientDeleteRequestDto;
import com.backtracking.MrDinner.domain.dinner.dto.DinnerIngredientFetchRequestDto;
import com.backtracking.MrDinner.domain.dinner.dto.DinnerIngredientUpdateRequestDto;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientList;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientRepository;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DinnerIngredientService {
    private final DinnerIngredientRepository dinnerIngredientRepository;
    private final DinnerRepository dinnerRepository;
    public void createDinnerIngredient(DinnerIngredientCreateRequestDto requestDto) {
        if(dinnerIngredientRepository.existsById(requestDto.getDinnerIngredient())) {
            throw new IllegalArgumentException("이미 있는 재료입니다.");
        }
        DinnerList dinnerList = dinnerRepository.findById(requestDto.getDinner()).orElseThrow(() -> new IllegalArgumentException("없는 디너입니다."));
        dinnerIngredientRepository.save(requestDto.toEntity(requestDto.getDinnerIngredient(), dinnerList, requestDto.getPrice(), requestDto.getQuantity(), requestDto.getDemandDate()));

    }

    @Transactional
    public List<DinnerIngredientList> fetchDinnerAllIngredient(DinnerIngredientFetchRequestDto requestDto) {
        List<DinnerIngredientList> dinnerIngredientLists = dinnerIngredientRepository.findAll();

        for (DinnerIngredientList dinnerIngredientList : dinnerIngredientLists){
            dinnerIngredientList.getDinner().getDinner();
        }
        if(dinnerIngredientLists.isEmpty()){
            throw new IllegalArgumentException("디너 세부 재료가 없습니다.");
        }
        else{
            return dinnerIngredientLists;
        }
    }

    @Transactional
    public List<DinnerIngredientList> fetchDinnerIngredient(DinnerIngredientFetchRequestDto requestDto) {
        DinnerIngredientList dinnerIngredientList = dinnerIngredientRepository.findById(requestDto.getDinnerIngredient()).orElseThrow(() -> new IllegalArgumentException("디너 재료가 없습니다."));
        List<DinnerIngredientList> dinnerIngredientLists = new ArrayList<>();

        dinnerIngredientLists.add(dinnerIngredientList);

        return dinnerIngredientLists;
    }

    @Transactional
    public void updateDinnerIngredient(DinnerIngredientUpdateRequestDto requestDto) {
        DinnerIngredientList dinnerIngredientList = dinnerIngredientRepository.findById(requestDto.getDinnerIngredient()).orElseThrow(() -> new IllegalArgumentException("해당 디너 재고가 없습니다."));
        dinnerIngredientList.update(requestDto.getQuantity(), requestDto.getDemandDate());
    }

    @Transactional
    public void deleteDinnerIngredient(DinnerIngredientDeleteRequestDto requestDto) {
        DinnerIngredientList dinnerIngredient = dinnerIngredientRepository.findById(requestDto.getDinnerIngredient()).orElseThrow(() -> new IllegalArgumentException("해당 디너 재고가 없습니다."));
        dinnerIngredientRepository.delete(dinnerIngredient);
    }
}
