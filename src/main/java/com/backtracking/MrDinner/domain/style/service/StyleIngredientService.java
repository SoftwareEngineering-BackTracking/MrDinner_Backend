package com.backtracking.MrDinner.domain.style.service;

import com.backtracking.MrDinner.domain.style.dto.StyleIngredientCreateRequestDto;
import com.backtracking.MrDinner.domain.style.dto.StyleIngredientDeleteRequestDto;
import com.backtracking.MrDinner.domain.style.dto.StyleIngredientFetchRequestDto;
import com.backtracking.MrDinner.domain.style.dto.StyleIngredientUpdateRequestDto;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientList;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StyleIngredientService {
    private final StyleIngredientRepository styleIngredientRepository;

    @Transactional
    public void createStyleIngredient(StyleIngredientCreateRequestDto requestDto) {
        if(styleIngredientRepository.existsById(requestDto.getStyleIngredient())) {
            throw new IllegalArgumentException("이미 있는 재료입니다.");
        }

        styleIngredientRepository.save(requestDto.toEntity(requestDto.getStyleIngredient(), requestDto.getStyle(), requestDto.getPrice(), requestDto.getQuantity(), requestDto.getDemandDate()));

    }

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

    @Transactional
    public void updateStyleIngredient(StyleIngredientUpdateRequestDto requestDto) {
        StyleIngredientList styleIngredient = styleIngredientRepository.findById(requestDto.getStyleIngredient()).orElseThrow(() -> new IllegalArgumentException("해당 디너 재고가 없습니다."));
        styleIngredient.update(requestDto.getQuantity(), requestDto.getDemandDate());
    }

    @Transactional
    public void deleteStyleIngredient(StyleIngredientDeleteRequestDto requestDto) {
        StyleIngredientList styleIngredient = styleIngredientRepository.findById(requestDto.getStyleIngredient()).orElseThrow(() -> new IllegalArgumentException("해당 디너 재고가 없습니다."));
        styleIngredientRepository.delete(styleIngredient);
    }

    @Transactional
    public List<StyleIngredientList> fetchStyleIngredient(StyleIngredientFetchRequestDto requestDto) {
        StyleIngredientList styleIngredientList = styleIngredientRepository.findById(requestDto.getStyleIngredient()).orElseThrow(() -> new IllegalArgumentException("디너 재료가 없습니다."));
        List<StyleIngredientList> styleIngredientLists = new ArrayList<>();

        styleIngredientLists.add(styleIngredientList);
        return styleIngredientLists;


    }
}
