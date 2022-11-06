package com.backtracking.MrDinner.domain.style.service;

import com.backtracking.MrDinner.domain.style.dto.StyleFetchRequestDto;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
import com.backtracking.MrDinner.domain.style.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StyleService {
    private final StyleRepository styleRepository;

    @Transactional
    public List<StyleList> fetchStyle(StyleFetchRequestDto requestDto) {
        StyleList style = styleRepository.findById(requestDto.getStyle()).orElseThrow(() -> new IllegalArgumentException("해당 스타일 메뉴가 없습니다."));
        List<StyleList> StyleLists = new ArrayList<>();
        StyleLists.add(style);

        return StyleLists;
    }

    @Transactional
    public List<StyleList> fetchAllStyle(StyleFetchRequestDto requestDto) {
        List<StyleList> styleLists = styleRepository.findAll();

        if(styleLists.isEmpty()){
            throw new IllegalArgumentException("디너 메뉴가 없습니다.");
        }
        else{
            return styleLists;
        }
    }
}
