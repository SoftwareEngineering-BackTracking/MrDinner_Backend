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
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final CartAddressRepository cartAddressRepository;
    private final UserRepository userRepository;

    public void createAddress(AddressCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Cart cart = cartRepository.findByUserId(user);
        Address duplicateAddress = addressRepository.findByDetailAndUserId(requestDto.getDetail(), user);

        CartAddress cartAddress = new CartAddress();
        cartAddress.setCartNo(cart);
        cartAddress.setDetail(requestDto.getDetail());

        if(duplicateAddress == null){
            // 이전에 없던 새로운 주소
            Address address = addressRepository.save(requestDto.toEntity(user));
            cartAddress.setAddress(address);
            cartAddressRepository.save(cartAddress);
        }
        else{
            if (cartAddressRepository.findByCartNo(cart) == null){
                // 기존에 있던 주소
                cartAddress.setAddress(duplicateAddress);
                cartAddressRepository.save(cartAddress);
            }
            else{
                cartAddressRepository.deleteByCartNo(cart);
                cartAddress.setAddress(duplicateAddress);
                cartAddressRepository.save(cartAddress);
            }
        }
    }

    @Transactional
    public List<Address> fetchMyAddress(AddressFetchRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        List<Address> addressList = addressRepository.findAllByUserId(user);

        if(addressList.isEmpty()){
            throw new IllegalArgumentException("저장된 주소가 없습니다.");
        }
        else{
            return addressList;
        }
    }

    @Transactional
    public List<Address> fetchAddress(AddressFetchRequestDto requestDto) {
        Address address = addressRepository.findById(requestDto.getAddressNo()).orElseThrow(() -> new IllegalArgumentException("해당 주소가 없습니다."));

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        return addressList;
    }

    @Transactional
    public void updateAddress(AddressUpdateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Address address = addressRepository.findAllByAddressNoAndUserId(requestDto.getAddressNo(), user);
        if (address == null) {
            throw new IllegalArgumentException("해당 주소가 없습니다.");
        }
        address.update(requestDto.getDetail());

    }
    @Transactional
    public void deleteAddress(AddressDeleteRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Address address= addressRepository.findAllByAddressNoAndUserId(requestDto.getAddressNo(), user);
        if (address == null) {
            throw new IllegalArgumentException("해당 주소가 없습니다.");
        }
        addressRepository.delete(address);
    }

}
