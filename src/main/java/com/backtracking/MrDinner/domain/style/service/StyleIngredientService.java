package com.backtracking.MrDinner.domain.style.service;

import com.backtracking.MrDinner.domain.style.dto.StyleIngredientFetchRequestDto;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientList;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientRepository;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StyleIngredientService {
    private final StyleIngredientRepository styleIngredientRepository;
    @Transactional
    public List<StyleIngredientList> fetchAllStyleIngredient(StyleIngredientFetchRequestDto requestDto) {
        List<StyleIngredientList> styleIngredientList = styleIngredientRepository.findAll();

        if(styleIngredientList.isEmpty()){
            throw new IllegalArgumentException("스타일 세부 재료가 없습니다.");
        }
        else{
            return styleIngredientList;
        }
    }
}
