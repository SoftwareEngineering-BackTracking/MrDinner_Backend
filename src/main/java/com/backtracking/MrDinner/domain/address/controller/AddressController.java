package com.backtracking.MrDinner.domain.address.controller;

import com.backtracking.MrDinner.domain.address.dto.*;
import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.domain.address.service.AddressService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressCreateResponseDto> createAddress(@RequestBody AddressCreateRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            addressService.createAddress(requestDto, session);
            dtoMetaData = new DtoMetaData("주소 설정 완료");
            return ResponseEntity.ok(new AddressCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AddressCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<AddressFetchResponseDto> fetchAddress(@RequestBody AddressFetchRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            if(requestDto.getAddressNo() == null){
                List<Address> addressList = addressService.fetchMyAddress(requestDto, session);
                dtoMetaData = new DtoMetaData("나의 주소 조회 완료");
                return ResponseEntity.ok(new AddressFetchResponseDto(dtoMetaData, addressList));
            }
            else{
                List<Address> addressList = addressService.fetchAddress(requestDto);
                dtoMetaData = new DtoMetaData("특정 주소 조회 완료");
                return ResponseEntity.ok(new AddressFetchResponseDto(dtoMetaData, addressList));
            }

        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AddressFetchResponseDto(dtoMetaData));
        }
    }

    @PutMapping
    public ResponseEntity<AddressUpdateResponseDto> updateAddress(@RequestBody AddressUpdateRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            addressService.updateAddress(requestDto, session);
            dtoMetaData = new DtoMetaData("주소 수정 완료");
            return ResponseEntity.ok(new AddressUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AddressUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<AddressDeleteResponseDto> deleteAddress(@RequestBody AddressDeleteRequestDto requestDto, HttpSession session){

        DtoMetaData dtoMetaData;

        try{
            addressService.deleteAddress(requestDto, session);
            dtoMetaData = new DtoMetaData("주소 삭제 완료");
            return ResponseEntity.ok(new AddressDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AddressDeleteResponseDto(dtoMetaData));
        }
    }
}
