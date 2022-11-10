package com.backtracking.MrDinner.domain.purchase.service;

import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.purchase.dto.PurchaseCreateRequestDto;
import com.backtracking.MrDinner.domain.purchase.dto.PurchaseDeleteRequestDto;
import com.backtracking.MrDinner.domain.purchase.dto.PurchaseFetchRequestDto;
import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
import com.backtracking.MrDinner.domain.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    @Transactional
    public void createPurchase(PurchaseCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        purchaseRepository.save(requestDto.toEntity(id));
    }

    @Transactional
    public List<Purchase> fetchPurchase(PurchaseFetchRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");

        List<Purchase> purchaseList = purchaseRepository.findAllByUserId(id);

        if(purchaseList.isEmpty()){
            throw new IllegalArgumentException("가지고 있는 결제 수단이 없습니다.");
        }
        return purchaseList;
    }

    @Transactional
    public void deletePurchase(PurchaseDeleteRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Purchase purchase = purchaseRepository.findAllByPurchaseNoAndUserId(requestDto.getPurchaseNo(), id);
        if (purchase == null) {
            throw new IllegalArgumentException("해당 결제 정보가 없습니다.");
        }
        purchaseRepository.delete(purchase);
    }
}
