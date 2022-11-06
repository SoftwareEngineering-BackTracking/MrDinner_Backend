package com.backtracking.MrDinner.domain.demand.service;

import com.backtracking.MrDinner.domain.address.repository.CartAddress;
import com.backtracking.MrDinner.domain.address.repository.CartAddressRepository;
import com.backtracking.MrDinner.domain.cart.repository.*;
import com.backtracking.MrDinner.domain.demand.dto.DemandCancelRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandCreateRequestDto;
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

        // item & detail 저장
        List<CartItem> cartItemList = cartItemRepository.findAllByCartNo(cart.getCartNo());
        if(cartItemList.isEmpty()){
            throw new IllegalArgumentException("장바구니에 담긴 주문이 없습니다.");
        }
        Long demandNo = demand.getDemandno();

        for(int i = 0 ; i < cartItemList.size() ; i++){
            DemandItem demandItem = new DemandItem();
            demandItem.setDemandNo(demandNo);
            demandItem.setDinner(cartItemList.get(i).getDinner());
            demandItem.setStyle(cartItemList.get(i).getStyle());

            DemandItem savedDemandItem = demandItemRepository.saveAndFlush(demandItem);

            Long cartItemNo = cartItemList.get(i).getCartItemNo();

            List<CartDetail> cartDetailList = cartDetailRepository.findAllByCartItemNo(cartItemNo);

            List<DemandDetail> demandDetailList = new ArrayList<>();
            for(int j = 0 ; j < cartDetailList.size() ; j++){
                DemandDetail demandDetail = new DemandDetail();
                demandDetail.setDemandItemNo(savedDemandItem.getDemandItemNo());
                demandDetail.setName(cartDetailList.get(j).getName());
                demandDetail.setStatus(cartDetailList.get(j).getStatus());
                demandDetailList.add(demandDetail);
            }
            demandDetailRepository.saveAllAndFlush(demandDetailList);
        }

        // 장바구니 안 정보 삭제
//        cartItemRepository.deleteAllByCartNo(cart.getCartNo());
//        for(int i = 0 ; i < cartItemList.size() ; i++){
//            Long cartItemNo = cartItemList.get(i).getCartItemNo();
//            cartDetailRepository.deleteAllByCartItemNo(cartItemNo);
//        }

    }

    @Transactional
    public OrderInfo fetchDemand(DemandFetchRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        List<Demand> demandList = demandRepository.findAllByUserId(id);
        List<List<DemandItem>> demandItemList = new ArrayList<>();
        List<List<DemandDetail>> demandDetailList = new ArrayList<>();

        for(int i = 0 ; i < demandList.size() ; i++){
                List<DemandItem> demandItem = demandItemRepository.findAllByDemandNo(demandList.get(i).getDemandno());
                demandItemList.add(demandItem);
            for(int j = 0; j < demandItem.size() ; j++){
                List<DemandDetail> demandDetail = demandDetailRepository.findAllByDemandItemNo(demandItem.get(j).getDemandItemNo());
                demandDetailList.add(demandDetail);
            }
        }
        OrderInfo orderInfo = new OrderInfo(demandList, demandItemList, demandDetailList);
        return orderInfo;
    }

    @Transactional
    public List<Demand> fetchAllDemand(DemandFetchRequestDto requestDto) {
        List<Demand> demandList = demandRepository.findAll();
        return demandList;
    }

    @Transactional
    public void updateDemand(DemandUpdateRequestDto requestDto, HttpSession session) {
        Demand demand = demandRepository.findById(requestDto.getDemandNo()).orElseThrow(() -> new IllegalArgumentException("주문 정보가 없습니다."));

        // price update

        // demandItem update
        List<DemandItem> updateDemandItemList = requestDto.getDemandItemList();
        if(updateDemandItemList != null){
            for(int i = 0 ; i < updateDemandItemList.size() ; i++){
                Long demandItemNo = updateDemandItemList.get(i).getDemandItemNo();
                DemandItem demandItem = demandItemRepository.findById(demandItemNo).orElseThrow(() -> new IllegalArgumentException("주문한 단일 주문이 없습니다."));
                demandItem.update(updateDemandItemList.get(i).getDinner(), updateDemandItemList.get(i).getStyle());
            }
        }


        // demandDetail update
        List<DemandDetail> updateDemandDetailList = requestDto.getDemandDetailList();
        if (updateDemandDetailList != null){
            for(int i = 0 ; i < updateDemandDetailList.size() ; i++){
                Long demandDetailNo = updateDemandDetailList.get(i).getDemandDetailNo();
                DemandDetail demandDetail = demandDetailRepository.findById(demandDetailNo).orElseThrow(() -> new IllegalArgumentException("주문 상세 정보가 없습니다."));
                demandDetail.update(updateDemandDetailList.get(i).getName(), updateDemandDetailList.get(i).getStatus());
            }
        }

    }

    @Transactional
    public void cancelDemand(DemandCancelRequestDto requestDto) {
        Demand demand = demandRepository.findById(requestDto.getDemandNo()).orElseThrow(()-> new IllegalArgumentException("취소할 주문이 없습니다."));
        demand.update(demand.getPrice(), DemandStatus.주문_취소, demand.getAddress(), demand.getCoupon(), demand.getPurchase());
    }

}
