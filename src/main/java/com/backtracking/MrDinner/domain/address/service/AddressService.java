package com.backtracking.MrDinner.domain.address.service;

import com.backtracking.MrDinner.domain.address.dto.AddressCreateRequestDto;
import com.backtracking.MrDinner.domain.address.dto.AddressDeleteRequestDto;
import com.backtracking.MrDinner.domain.address.dto.AddressFetchRequestDto;
import com.backtracking.MrDinner.domain.address.dto.AddressUpdateRequestDto;
import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.domain.address.repository.AddressRepository;
import com.backtracking.MrDinner.domain.address.repository.CartAddress;
import com.backtracking.MrDinner.domain.address.repository.CartAddressRepository;
import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final CartAddressRepository cartAddressRepository;

    public void createAddress(AddressCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Cart cart = cartRepository.findByUserId(id);
        Address duplicateAddress = addressRepository.findByDetailAndUserId(requestDto.getDetail(), id);

        CartAddress cartAddress = new CartAddress();
        cartAddress.setCartNo(cart.getCartNo());
        cartAddress.setDetail(requestDto.getDetail());

        if(duplicateAddress == null){
            // 이전에 없던 새로운 주소
            addressRepository.save(requestDto.toEntity(id));
            cartAddressRepository.save(cartAddress);
        }
        else{
            if (cartAddressRepository.findByCartNo(cart.getCartNo()) == null){
                // 기존에 있던 주소
                cartAddressRepository.save(cartAddress);
            }
            else{
                cartAddressRepository.deleteByCartNo(cart.getCartNo());
                cartAddressRepository.save(cartAddress);
            }
        }
    }
    @Transactional
    public List<Address> fetchAddress(AddressFetchRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        List<Address> addressList = addressRepository.findAllByUserId(id);

        if(addressList.isEmpty()){
            throw new IllegalArgumentException("저장된 주소가 없습니다.");
        }
        else{
            return addressList;
        }
    }
    @Transactional
    public void updateAddress(AddressUpdateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Address address = addressRepository.findAllByAddressNoAndUserId(requestDto.getAddressNo(), id);
        if (address == null) {
            throw new IllegalArgumentException("해당 주소가 없습니다.");
        }
        address.update(requestDto.getDetail());

    }
    @Transactional
    public void deleteAddress(AddressDeleteRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Address address= addressRepository.findAllByAddressNoAndUserId(requestDto.getAddressNo(), id);
        if (address == null) {
            throw new IllegalArgumentException("해당 주소가 없습니다.");
        }
        addressRepository.delete(address);
    }
}
