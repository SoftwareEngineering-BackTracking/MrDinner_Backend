package com.backtracking.MrDinner.domain.address.dto;

import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import lombok.Data;

@Data
public class AddressCreateRequestDto {
    private String detail;

    public Address toEntity(String userId){
        return Address.builder()
                .userId(userId)
                .detail(detail)
                .build();
    }
}
