package com.backtracking.MrDinner.domain.purchase.service;

import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.purchase.dto.PurchaseCreateRequestDto;
import com.backtracking.MrDinner.domain.purchase.dto.PurchaseDeleteRequestDto;
import com.backtracking.MrDinner.domain.purchase.dto.PurchaseFetchRequestDto;
import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
import com.backtracking.MrDinner.domain.purchase.repository.PurchaseRepository;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    @Transactional
    public void createPurchase(PurchaseCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        purchaseRepository.save(requestDto.toEntity(user));
    }

    @Transactional
    public List<Purchase> fetchMyPurchase(PurchaseFetchRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        List<Purchase> purchaseList = purchaseRepository.findAllByUserId(user);

        if(purchaseList.isEmpty()){
            throw new IllegalArgumentException("가지고 있는 결제 수단이 없습니다.");
        }
        return purchaseList;
    }

    @Transactional
    public void deletePurchase(PurchaseDeleteRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Purchase purchase = purchaseRepository.findAllByPurchaseNoAndUserId(requestDto.getPurchaseNo(), user);
        if (purchase == null) {
            throw new IllegalArgumentException("해당 결제 정보가 없습니다.");
        }
        purchaseRepository.delete(purchase);
    }

    @Transactional
    public List<Purchase> fetchPurchase(PurchaseFetchRequestDto requestDto, HttpSession session) {
        Purchase purchase = purchaseRepository.findById(requestDto.getPurchaseNo()).orElseThrow(() -> new IllegalArgumentException("해당 결제 정보가 없습니다."));

        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(purchase);

        return purchaseList;
    }
}
