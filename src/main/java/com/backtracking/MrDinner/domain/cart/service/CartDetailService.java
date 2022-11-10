package com.backtracking.MrDinner.domain.cart.service;

import com.backtracking.MrDinner.domain.cart.dto.CartDetailCreateRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartDetailDeleteRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartDetailFetchRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartDetailUpdateRequestDto;
import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import com.backtracking.MrDinner.domain.cart.repository.CartDetailRepository;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientList;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientRepository;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientList;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientRepository;
import com.backtracking.MrDinner.global.enumpackage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartDetailService {

    private final CartDetailRepository cartDetailRepository;
    private final DinnerIngredientRepository dinnerIngredientRepository;
    private final StyleIngredientRepository styleIngredientRepository;

    public void createCartDetail(CartDetailCreateRequestDto requestDto) {
        List<String> name = requestDto.getName();
        List<DetailStatus> detailStatuses = requestDto.getStatus();
        List<DinnerStyle> dinnerStyles = requestDto.getDinnerStyles();
        List<CartDetail> cartDetails = new ArrayList<>();

        for(int i = 0 ; i < name.size() ; i++){
            if(dinnerStyles.get(i) == DinnerStyle.디너){
                DinnerIngredientList dinnerIngredientList = dinnerIngredientRepository.findById(DinnerIngredient.valueOf(name.get(i))).orElseThrow(() ->new IllegalArgumentException("세부 요청사항에 대한 메뉴가 없습니다."));
                if(detailStatuses.get(i) == DetailStatus.추가){
                    cartDetails.add(requestDto.toEntity(i, dinnerIngredientList.getPrice()));
                }
                else if(detailStatuses.get(i) == DetailStatus.삭제){
                    cartDetails.add(requestDto.toEntity(i, -dinnerIngredientList.getPrice()));
                }
            }
            else{
                StyleIngredientList styleIngredientList = styleIngredientRepository.findById(StyleIngredient.valueOf(name.get(i))).orElseThrow(() -> new IllegalArgumentException("세부 요청사항에 대한 메뉴가 없습니다."));
                if(detailStatuses.get(i) == DetailStatus.추가){
                    cartDetails.add(requestDto.toEntity(i, styleIngredientList.getPrice()));
                }
                else if(detailStatuses.get(i) == DetailStatus.삭제){
                    cartDetails.add(requestDto.toEntity(i, -styleIngredientList.getPrice()));
                }
            }
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

}
