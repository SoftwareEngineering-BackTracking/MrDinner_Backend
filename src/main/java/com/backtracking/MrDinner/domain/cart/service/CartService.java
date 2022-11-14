package com.backtracking.MrDinner.domain.cart.service;

import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartRepository;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    @Transactional
    public void createCart(String id){
        Cart cart = new Cart();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        cart.setUserId(user);
        cartRepository.save(cart);
    }
}
