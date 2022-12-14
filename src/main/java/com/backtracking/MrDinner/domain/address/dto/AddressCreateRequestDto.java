package com.backtracking.MrDinner.domain.address.dto;

import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import com.backtracking.MrDinner.domain.user.repository.User;
import lombok.Data;

@Data
public class AddressCreateRequestDto {
    private String id;
    private String detail;

    public Address toEntity(User userId){
        return Address.builder()
                .userId(userId)
                .detail(detail)
                .build();
    }
}
