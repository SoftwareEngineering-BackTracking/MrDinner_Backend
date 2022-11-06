package com.backtracking.MrDinner.domain.dinner.service;

import com.backtracking.MrDinner.domain.dinner.dto.DinnerIngredientFetchRequestDto;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientList;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientRepository;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DinnerIngredientService {
    private final DinnerIngredientRepository dinnerIngredientRepository;

    @Transactional
    public List<DinnerIngredientList> fetchDinnerAllIngredient(DinnerIngredientFetchRequestDto requestDto) {
        List<DinnerIngredientList> dinnerIngredientLists = dinnerIngredientRepository.findAll();

        if(dinnerIngredientLists.isEmpty()){
            throw new IllegalArgumentException("디너 세부 재료가 없습니다.");
        }
        else{
            return dinnerIngredientLists;
        }
    }
}
