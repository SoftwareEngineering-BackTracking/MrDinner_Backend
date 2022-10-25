package com.backtracking.MrDinner.domain.cart.service;

import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public void createCart(String id){
        Cart cart = new Cart();
        cart.setUserId(id);
        cartRepository.save(cart);
    }
}
