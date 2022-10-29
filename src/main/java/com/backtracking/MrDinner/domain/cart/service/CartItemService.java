package com.backtracking.MrDinner.domain.cart.service;

import com.backtracking.MrDinner.domain.cart.dto.CartItemCreateRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartItemDeleteRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartItemFetchRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartItemUpdateRequestDto;
import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartItem;
import com.backtracking.MrDinner.domain.cart.repository.CartItemRepository;
import com.backtracking.MrDinner.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public void createCartItem(CartItemCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Cart cart = cartRepository.findByUserId(id);

        cartItemRepository.save(requestDto.toEntity(cart.getCartNo()));
    }

    @Transactional
    public List<CartItem> fetchCartItem(CartItemFetchRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Cart cart = cartRepository.findByUserId(id);
        List<CartItem> cartItems = cartItemRepository.findByCartNo(cart.getCartNo());
        return cartItems;

    }

    @Transactional
    public void updateCartItem(CartItemUpdateRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findById(requestDto.getCartItemNo()).orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다."));

        cartItem.update(requestDto.getDinner(), requestDto.getStyle());
    }

    @Transactional
    public void deleteCartItem(CartItemDeleteRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findById(requestDto.getCartItemNo()).orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다."));

        cartItemRepository.delete(cartItem);
    }


}
