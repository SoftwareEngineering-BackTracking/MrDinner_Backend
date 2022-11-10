package com.backtracking.MrDinner.domain.cart.service;

import com.backtracking.MrDinner.domain.cart.dto.PaymentRequestDto;
import com.backtracking.MrDinner.domain.cart.repository.*;
import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.coupon.repository.CouponRepository;
import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
import com.backtracking.MrDinner.domain.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final CouponRepository couponRepository;
    private final CartCouponRepository cartCouponRepository;
    private final PurchaseRepository purchaseRepository;
    private final CartPurchaseRepository cartPurchaseRepository;
    private final CartRepository cartRepository;

    public void payCart(PaymentRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Cart cart = cartRepository.findByUserId(id);

        if(requestDto.getCouponNo() != null) {
            Coupon coupon = couponRepository.findByCouponNoAndUserId(requestDto.getCouponNo(), id);
            if(coupon == null){
                throw new IllegalArgumentException("해당 쿠폰이 없습니다.");
            }
            CartCoupon cartCoupon = new CartCoupon();
            cartCoupon.setName(coupon.getName());
            cartCoupon.setPrice(coupon.getPrice());
            cartCoupon.setStartTime(coupon.getStartTime());
            cartCoupon.setEndTime(coupon.getEndTime());
            cartCoupon.setCartNo(cart.getCartNo());
            cartCouponRepository.save(cartCoupon);
        }

        Purchase purchase = purchaseRepository.findById(requestDto.getPurchaseNo()).orElseThrow(() -> new IllegalArgumentException("해당 결제 정보가 없습니다."));
        if(purchase == null){
            throw new IllegalArgumentException("해당 결제 정보가 없습니다.");
        }

        CartPurchase cartPurchase = new CartPurchase();
        cartPurchase.setCardNumber(purchase.getCardNumber());
        cartPurchase.setBank(purchase.getBank());
        cartPurchase.setCartNo(cart.getCartNo());
        cartPurchaseRepository.save(cartPurchase);
    }
}
