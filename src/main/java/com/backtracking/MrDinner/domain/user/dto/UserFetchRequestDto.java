package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.Data;

@Data
public class UserFetchRequestDto {
    // fetch
    private String id;

    // fetchFilter
    private String name;

    // fetchFilter
    private Department Department;

}
