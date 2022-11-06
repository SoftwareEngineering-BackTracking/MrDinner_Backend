package com.backtracking.MrDinner.domain.cart.service;

import com.backtracking.MrDinner.domain.cart.dto.CartItemCreateRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartItemDeleteRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartItemFetchRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartItemUpdateRequestDto;
import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartItem;
import com.backtracking.MrDinner.domain.cart.repository.CartItemRepository;
import com.backtracking.MrDinner.domain.cart.repository.CartRepository;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerRepository;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
import com.backtracking.MrDinner.domain.style.repository.StyleRepository;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
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
    private final DinnerRepository dinnerRepository;
    private final StyleRepository styleRepository;
    @Transactional
    public void createCartItem(CartItemCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        Cart cart = cartRepository.findByUserId(id);
        DinnerList dinner = dinnerRepository.findById(requestDto.getDinner()).orElseThrow(() -> new IllegalArgumentException("해당 디너가 없습니다."));
        StyleList style = styleRepository.findById(requestDto.getStyle()).orElseThrow(() -> new IllegalArgumentException("해당 스타일이 없습니다."));
        Long price = dinner.getPrice() + style.getPrice();
        cartItemRepository.save(requestDto.toEntity(cart.getCartNo(), price));
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
