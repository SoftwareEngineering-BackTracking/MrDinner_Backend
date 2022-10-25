package com.backtracking.MrDinner.domain.cart.service;

import com.backtracking.MrDinner.domain.cart.dto.CartDetailCreateRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartDetailDeleteRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartDetailFetchRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartDetailUpdateRequestDto;
import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import com.backtracking.MrDinner.domain.cart.repository.CartDetailRepository;
import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDetailService {

    private final CartDetailRepository cartDetailRepository;


    public void createCartDetail(CartDetailCreateRequestDto requestDto) {
        List<String> name = requestDto.getName();

        List<CartDetail> cartDetails = new ArrayList<>();

        for(int i = 0 ; i < name.size() ; i++){
            cartDetails.add(requestDto.toEntity(i));
        }
        cartDetailRepository.saveAll(cartDetails);
    }

    @Transactional
    public List<CartDetail> fetchCartDetail(CartDetailFetchRequestDto requestDto) {
        List<CartDetail> cartDetails = cartDetailRepository.findByCartItemNo(requestDto.getCartItemNo());
        if(cartDetails.isEmpty()){
            throw new IllegalArgumentException("세부사항이 없습니다.");
        }
        return cartDetails;
    }

    @Transactional
    public void updateCartDetail(CartDetailUpdateRequestDto requestDto) {
        List<CartDetail> cartDetails = cartDetailRepository.findByCartItemNo(requestDto.getCartItemNo());
        if(cartDetails.isEmpty()){
            throw new IllegalArgumentException("세부사항이 없습니다.");
        }

        for(int i = 0 ; i < cartDetails.size() ; i++){
            CartDetail cartDetail = cartDetails.get(i);
            cartDetail.update(requestDto.getName().get(i), requestDto.getStatus().get(i));
        }
    }

    @Transactional
    public void deleteCartDetail(CartDetailDeleteRequestDto requestDto) {
        List<CartDetail> cartDetails = cartDetailRepository.findByCartItemNo(requestDto.getCartItemNo());
        if(cartDetails.isEmpty()){
            throw new IllegalArgumentException("세부사항이 없습니다.");
        }

        cartDetailRepository.deleteAll(cartDetails);
    }

    @Transactional
    public void deleteAllCartDetail() {
        cartDetailRepository.deleteAll();
    }
}
