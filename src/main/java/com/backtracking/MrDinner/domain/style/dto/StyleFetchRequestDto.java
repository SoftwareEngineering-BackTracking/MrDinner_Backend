package com.backtracking.MrDinner.domain.style.dto;

import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StyleFetchRequestDto {
    private Style style;
}
