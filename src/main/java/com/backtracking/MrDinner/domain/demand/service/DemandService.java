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

    public Demand toEntity(String userId, Long price, DemandStatus status, Long address, Long coupon, Long purchase){
        return Demand.builder()
                .userId(userId)
                .price(null)
                .status(status)
                .address(address)
                .coupon(coupon)
                .purchase(purchase)
                .build();
    }

    public void createDemand(DemandCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Cart cart = cartRepository.findByUserId(id);

        // demand price, status, address, coupon, purchase 저장

        // price
        Long price = Long.valueOf(0);

        // status
        DemandStatus status = DemandStatus.주문_대기;

        // address
        CartAddress cartAddress = cartAddressRepository.findByCartNo(cart.getCartNo());
        Long address = cartAddress.getCartAddressNo();
        //cartAddressRepository.deleteByCartNo(cart.getCartNo());

        // coupon
        CartCoupon cartCoupon = cartCouponRepository.findByCartNo(cart.getCartNo());
        Long coupon = null;
        if(cartCoupon != null){
            coupon = cartCoupon.getCartCouponNo();
        }
        //cartCouponRepository.deleteByCartNo(cart.getCartNo());

        // purchase
        CartPurchase cartPurchase = cartPurchaseRepository.findByCartNo(cart.getCartNo());
        Long purchase=  cartPurchase.getCartPurchaseNo();
        //cartPurchaseRepository.deleteByCartNo(cart.getCartNo());

        Demand demand = demandRepository.saveAndFlush(toEntity(id, price, status, address, coupon, purchase));

        // item 저장
        List<CartItem> cartItemList = cartItemRepository.findAllByCartNo(cart.getCartNo());
        if(cartItemList.isEmpty()){
            throw new IllegalArgumentException("장바구니에 담긴 주문이 없습니다.");
        }
        List<DemandItem> demandItemList = new ArrayList<>();
        Long demandNo = demand.getDemandno();

        for(int i = 0 ; i < cartItemList.size() ; i++){
            DemandItem demandItem = new DemandItem();
            demandItem.setDemandNo(demandNo);
            demandItem.setDinner(cartItemList.get(i).getDinner());
            demandItem.setStyle(cartItemList.get(i).getStyle());
            demandItemList.add(demandItem);
        }
        List<DemandItem> savedDemandItemList = demandItemRepository.saveAllAndFlush(demandItemList);

        // detail 저장
        List<CartDetail> cartDetailList = new ArrayList<>();
        List<Long> demandItemNoList = new ArrayList<>();
        for(int i = 0 ; i < cartItemList.size() ; i++){
            Long cartItemNo = cartItemList.get(i).getCartItemNo();
            cartDetailList.add(cartDetailRepository.findAllByCartItemNo(cartItemNo));
            demandItemNoList.add(savedDemandItemList.get(i).getDemandItemNo());
        }

        List<DemandDetail> demandDetailList = new ArrayList<>();

        for(int i = 0 ; i < cartDetailList.size() ; i++){
            DemandDetail demandDetail = (DemandDetail) cartDetailList;
            demandDetailList.add(demandDetail);
        }
        demandDetailRepository.saveAll(demandDetailList);

        // 장바구니 안 정보 삭제제
       cartItemRepository.deleteAllByCartNo(cart.getCartNo());
        for(int i = 0 ; i < cartItemList.size() ; i++){
            Long cartItemNo = cartItemList.get(i).getCartItemNo();
            cartDetailRepository.deleteAllByCartItemNo(cartItemNo);
        }

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
