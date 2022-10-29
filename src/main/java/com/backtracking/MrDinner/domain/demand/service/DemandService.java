package com.backtracking.MrDinner.domain.demand.service;

import com.backtracking.MrDinner.domain.address.repository.CartAddress;
import com.backtracking.MrDinner.domain.address.repository.CartAddressRepository;
import com.backtracking.MrDinner.domain.cart.repository.*;
import com.backtracking.MrDinner.domain.demand.dto.DemandCreateRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandDeleteRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandFetchRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandUpdateRequestDto;
import com.backtracking.MrDinner.domain.demand.repository.*;
import com.backtracking.MrDinner.global.enumpackage.DemandStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DemandService {
    private final DemandRepository demandRepository;
    private final DemandItemRepository demandItemRepository;
    private final DemandDetailRepository demandDetailRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartDetailRepository cartDetailRepository;
    private final CartAddressRepository cartAddressRepository;
    private final CartCouponRepository cartCouponRepository;
    private final CartPurchaseRepository cartPurchaseRepository;

    public void createDemand(DemandCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Cart cart = cartRepository.findByUserId(id);
        // item 저장
        List<CartItem> cartItemList = cartItemRepository.findAllByCartNo(cart.getCartNo());
        if(cartItemList.isEmpty()){
            throw new IllegalArgumentException("장바구니에 담긴 주문이 없습니다.");
        }
        List<DemandItem> demandItemList = new ArrayList<>();
        for(int i = 0 ; i < cartItemList.size() ; i++){
            DemandItem demandItem = (DemandItem) cartItemList;
            demandItemList.add(demandItem);
        }
        demandItemRepository.saveAll(demandItemList);
        cartItemRepository.deleteAllByCartNo(cart.getCartNo());

        // detail 저장
        List<CartDetail> cartDetailList = new ArrayList<>();

        for(int i = 0 ; i < cartItemList.size() ; i++){
            Long cartItemNo = cartItemList.get(i).getCartItemNo();
            cartDetailList.add(cartDetailRepository.findAllByCartItemNo(cartItemNo));
        }

        List<DemandDetail> demandDetailList = new ArrayList<>();
        for(int i = 0 ; i < cartDetailList.size() ; i++){
            DemandDetail demandDetail = (DemandDetail) cartDetailList;
            demandDetailList.add(demandDetail);
        }
        demandDetailRepository.saveAll(demandDetailList);
        for(int i = 0 ; i < cartItemList.size() ; i++){
            Long cartItemNo = cartItemList.get(i).getCartItemNo();
            cartDetailRepository.deleteAllByCartItemNo(cartItemNo);
        }
        // demand price, status, address, coupon, purchase 저장
        Demand demand = new Demand();
        // price

        // status
        demand.setStatus(DemandStatus.주문_대기);

        // address
        demand.setAddress(cartAddressRepository.findByCartNo(cart.getCartNo()));
        cartAddressRepository.deleteByCartNo(cart.getCartNo());

        // coupon
        demand.setCoupon(cartCouponRepository.findByCartNo(cart.getCartNo()));
        cartCouponRepository.deleteByCartNo(cart.getCartNo());
        // purchase
        demand.setPurchase(cartPurchaseRepository.findByCartNo(cart.getCartNo()));
        cartPurchaseRepository.deleteByCartNo(cart.getCartNo());


    }

    @Transactional
    public void fetchDemand(DemandFetchRequestDto requestDto, HttpSession session) {
    }

    @Transactional
    public void updateDemand(DemandUpdateRequestDto requestDto, HttpSession session) {
    }

    @Transactional
    public void deleteDemand(DemandDeleteRequestDto requestDto) {
    }
}
