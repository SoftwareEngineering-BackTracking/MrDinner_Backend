package com.backtracking.MrDinner.domain.address.dto;

import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class AddressFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<Address> addressList;

    public AddressFetchResponseDto(DtoMetaData dtoMetaData, List<Address> addressList){
        this.dtoMetaData = dtoMetaData;
        this.addressList = addressList;
    }

    public AddressFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }


}
