package com.backtracking.MrDinner.domain.demand.service;

import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.domain.address.repository.AddressRepository;
import com.backtracking.MrDinner.domain.address.repository.CartAddress;
import com.backtracking.MrDinner.domain.address.repository.CartAddressRepository;
import com.backtracking.MrDinner.domain.cart.repository.*;
import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.coupon.repository.CouponRepository;
import com.backtracking.MrDinner.domain.demand.dto.DemandCancelRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandCreateRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandFetchRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandUpdateRequestDto;
import com.backtracking.MrDinner.domain.demand.repository.*;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientList;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientRepository;
import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
import com.backtracking.MrDinner.domain.purchase.repository.PurchaseRepository;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientList;
import com.backtracking.MrDinner.domain.style.repository.StyleIngredientRepository;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import com.backtracking.MrDinner.global.enumpackage.*;
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
    private final DinnerIngredientRepository dinnerIngredientRepository;
    private final StyleIngredientRepository styleIngredientRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final AddressRepository addressRepository;
    private final CouponRepository couponRepository;

    public Demand toEntity(User userId, Long price, DemandStatus status, Address address, Coupon coupon, Purchase purchase){
        return Demand.builder()
                .userId(userId)
                .price(null)
                .status(status)
                .address(address)
                .coupon(coupon)
                .purchase(purchase)
                .build();
    }

    public void createDemand(DemandCreateRequestDto requestDto) {
        //String id = (String) session.getAttribute("id");
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Cart cart = cartRepository.findByUserId(user);

        // demand price, status, address, coupon, purchase 저장

        // price
        Long price = Long.valueOf(0);

        // status
        DemandStatus status = DemandStatus.주문_대기;

        // address
        CartAddress cartAddress = cartAddressRepository.findByCartNo(cart);
        Address address = cartAddress.getAddress();
        cartAddressRepository.deleteByCartNo(cart);

        // coupon
        CartCoupon cartCoupon = cartCouponRepository.findByCartNo(cart);
        Coupon coupon = null;
        if(cartCoupon != null){
            coupon = cartCoupon.getCoupon();
        }
        cartCouponRepository.deleteByCartNo(cart);

        // purchase
        CartPurchase cartPurchase = cartPurchaseRepository.findByCartNo(cart);
        Purchase purchase = cartPurchase.getPurchase();
        cartPurchaseRepository.deleteByCartNo(cart);

        Demand demand = demandRepository.saveAndFlush(toEntity(user, price, status, address, coupon, purchase));

        // item & detail 저장
        List<CartItem> cartItemList = cartItemRepository.findAllByCartNo(cart);
        if(cartItemList.isEmpty()){
            throw new IllegalArgumentException("장바구니에 담긴 주문이 없습니다.");
        }

        for(int i = 0 ; i < cartItemList.size() ; i++){
            DemandItem demandItem = new DemandItem();
            demandItem.setDemandNo(demand);
            demandItem.setDinner(cartItemList.get(i).getDinner());
            demandItem.setStyle(cartItemList.get(i).getStyle());
            demandItem.setPrice(cartItemList.get(i).getPrice());
            price += demandItem.getPrice();

            List<DinnerIngredientList> dinnerIngredientLists = dinnerIngredientRepository.findAllByDinner(cartItemList.get(i).getDinner());
            for(int j = 0 ; j < dinnerIngredientLists.size() ; j++){
                Long quantity = dinnerIngredientLists.get(j).getQuantity();
                if(quantity <= 0){
                    throw new IllegalArgumentException("요리하기에 충분한 재고가 없습니다. 부족한 재고: "+dinnerIngredientLists.get(j).getDinnerIngredient());
                }
                quantity = quantity - 1;
                dinnerIngredientLists.get(j).update(quantity, dinnerIngredientLists.get(j).getDemandDate());
            }

            System.out.println("1");

            List<StyleIngredientList> styleIngredientLists = styleIngredientRepository.findAllByStyle(cartItemList.get(i).getStyle());
            for(int j = 0 ; j < styleIngredientLists.size() ; j++){
                Long quantity = styleIngredientLists.get(j).getQuantity();
                if(quantity <= 0){
                    throw new IllegalArgumentException("요리하기에 충분한 재고가 없습니다. 부족한 재고: "+styleIngredientLists.get(j).getStyleIngredient());
                }
                quantity = quantity - 1;
                styleIngredientLists.get(j).update(quantity, styleIngredientLists.get(j).getDemandDate());
            }
            DemandItem savedDemandItem = demandItemRepository.saveAndFlush(demandItem);
            List<CartDetail> cartDetailList = cartDetailRepository.findAllByCartItemNo(cartItemList.get(i));

            if(cartDetailList.isEmpty()){
                continue;
            }

            List<DemandDetail> demandDetailList = new ArrayList<>();
            for(int j = 0 ; j < cartDetailList.size() ; j++){
                DemandDetail demandDetail = new DemandDetail();
                demandDetail.setDemandItemNo(savedDemandItem);
                demandDetail.setName(cartDetailList.get(j).getName());
                demandDetail.setStatus(cartDetailList.get(j).getStatus());
                demandDetail.setPrice(cartDetailList.get(j).getPrice());
                demandDetail.setDinnerStyle(cartDetailList.get(j).getDinnerStyle());
                price += demandDetail.getPrice();

                if(cartDetailList.get(j).getDinnerStyle() == DinnerStyle.디너){
                    DinnerIngredientList dinnerIngredientList = dinnerIngredientRepository.findById(DinnerIngredient.valueOf(cartDetailList.get(j).getName())).orElseThrow(() ->new IllegalArgumentException("세부 요청사항에 대한 메뉴가 없습니다."));
                    if(cartDetailList.get(j).getStatus() == DetailStatus.추가){
                        Long quantity = dinnerIngredientList.getQuantity();
                        if(quantity <= 0){
                            throw new IllegalArgumentException("요리하기에 충분한 재고가 없습니다. 부족한 재고: "+dinnerIngredientList.getDinnerIngredient());
                        }
                        quantity = quantity - 1;
                        dinnerIngredientList.update(quantity, dinnerIngredientList.getDemandDate());
                    }
                    else if(cartDetailList.get(j).getStatus() == DetailStatus.삭제){
                        Long quantity = dinnerIngredientList.getQuantity() + 1;
                        dinnerIngredientList.update(quantity, dinnerIngredientList.getDemandDate());
                    }
                }
                else{
                    StyleIngredientList styleIngredientList = styleIngredientRepository.findById(StyleIngredient.valueOf(cartDetailList.get(j).getName())).orElseThrow(() -> new IllegalArgumentException("세부 요청사항에 대한 메뉴가 없습니다."));
                    if(cartDetailList.get(j).getStatus() == DetailStatus.추가){
                        Long quantity = styleIngredientList.getQuantity();
                        if(quantity <= 0){
                            throw new IllegalArgumentException("요리하기에 충분한 재고가 없습니다. 부족한 재고: "+styleIngredientList.getStyleIngredient());
                        }
                        quantity = quantity - 1;
                        styleIngredientList.update(quantity, styleIngredientList.getDemandDate());
                    }
                    else if(cartDetailList.get(j).getStatus() == DetailStatus.삭제){
                        Long quantity = styleIngredientList.getQuantity() + 1;
                        styleIngredientList.update(quantity, styleIngredientList.getDemandDate());
                    }
                }

                demandDetailList.add(demandDetail);
            }
            demandDetailRepository.saveAllAndFlush(demandDetailList);
        }

        demand.update(price, status, address, coupon, purchase);
        demandRepository.save(demand);


        // 장바구니 안 정보 삭제
        for(int i = 0 ; i < cartItemList.size() ; i++){
            System.out.println("aaaaaa");
            cartDetailRepository.deleteAllByCartItemNo(cartItemList.get(i));
        }
        cartItemRepository.deleteAllByCartNo(cart);

    }

    @Transactional
    public OrderInfo fetchDemand(DemandFetchRequestDto requestDto) {
        //String id = (String) session.getAttribute("id");
        User user = userRepository.findById(requestDto.getFilter()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        List<Demand> demandList = demandRepository.findAllByUserId(user);
        List<List<DemandItem>> demandItemList = new ArrayList<>();
        List<List<DemandDetail>> demandDetailList = new ArrayList<>();

        for(int i = 0 ; i < demandList.size() ; i++){
                List<DemandItem> demandItem = demandItemRepository.findAllByDemandNo(demandList.get(i));
                demandItemList.add(demandItem);
            for(int j = 0; j < demandItem.size() ; j++){
                List<DemandDetail> demandDetail = demandDetailRepository.findAllByDemandItemNo(demandItem.get(j));
                demandDetailList.add(demandDetail);
            }
        }
        OrderInfo orderInfo = new OrderInfo(demandList, demandItemList, demandDetailList);
        return orderInfo;
    }

    @Transactional
    public OrderInfo fetchAllDemand(DemandFetchRequestDto requestDto) {
        List<Demand> demandList = demandRepository.findAll();
        List<List<DemandItem>> demandItemList = new ArrayList<>();
        List<List<DemandDetail>> demandDetailList = new ArrayList<>();

        for(int i = 0 ; i < demandList.size() ; i++){
            List<DemandItem> demandItem = demandItemRepository.findAllByDemandNo(demandList.get(i));
            demandItemList.add(demandItem);
            for(int j = 0; j < demandItem.size() ; j++){
                List<DemandDetail> demandDetail = demandDetailRepository.findAllByDemandItemNo(demandItem.get(j));
                demandDetailList.add(demandDetail);
            }
        }
        OrderInfo orderInfo = new OrderInfo(demandList, demandItemList, demandDetailList);
        return orderInfo;
    }

    @Transactional
    public void updateDemand(DemandUpdateRequestDto requestDto) {
        Demand demand = demandRepository.findById(requestDto.getDemandNo()).orElseThrow(() -> new IllegalArgumentException("주문 정보가 없습니다."));

        // price update

        // demandItem update
        List<DemandItem> updateDemandItemList = requestDto.getDemandItemList();
        if(updateDemandItemList != null){
            for(int i = 0 ; i < updateDemandItemList.size() ; i++){
                Long demandItemNo = updateDemandItemList.get(i).getDemandItemNo();
                DemandItem demandItem = demandItemRepository.findById(demandItemNo).orElseThrow(() -> new IllegalArgumentException("주문한 단일 주문이 없습니다."));
                demandItem.update(updateDemandItemList.get(i).getDinner(), updateDemandItemList.get(i).getStyle(), updateDemandItemList.get(i).getPrice());
            }
        }


        // demandDetail update
        List<DemandDetail> updateDemandDetailList = requestDto.getDemandDetailList();
        if (updateDemandDetailList != null){
            for(int i = 0 ; i < updateDemandDetailList.size() ; i++){
                Long demandDetailNo = updateDemandDetailList.get(i).getDemandDetailNo();
                DemandDetail demandDetail = demandDetailRepository.findById(demandDetailNo).orElseThrow(() -> new IllegalArgumentException("주문 상세 정보가 없습니다."));
                demandDetail.update(updateDemandDetailList.get(i).getName(), updateDemandDetailList.get(i).getStatus(), updateDemandDetailList.get(i).getPrice(), updateDemandDetailList.get(i).getDinnerStyle());
            }
        }

    }

    @Transactional
    public void cancelDemand(DemandCancelRequestDto requestDto) {
        Demand demand = demandRepository.findById(requestDto.getDemandNo()).orElseThrow(()-> new IllegalArgumentException("취소할 주문이 없습니다."));
        demand.update(demand.getPrice(), DemandStatus.주문_취소, demand.getAddress(), demand.getCoupon(), demand.getPurchase());
    }

}
