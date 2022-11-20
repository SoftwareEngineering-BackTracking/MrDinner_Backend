package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFetchRequestDto {
    // fetch
    private String id;

    // fetchFilter
    private String name;

    // fetchFilter
    private Department department;

}
