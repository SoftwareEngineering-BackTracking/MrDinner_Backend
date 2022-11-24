package com.backtracking.MrDinner.domain.cart.service;

import com.backtracking.MrDinner.domain.cart.dto.*;
import com.backtracking.MrDinner.domain.cart.repository.*;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerRepository;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
import com.backtracking.MrDinner.domain.style.repository.StyleRepository;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import com.backtracking.MrDinner.global.voice.DinnerStyleVoice;
import com.backtracking.MrDinner.global.voice.VoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartDetailRepository cartDetailRepository;
    private final DinnerRepository dinnerRepository;
    private final StyleRepository styleRepository;
    private final UserRepository userRepository;
    private final VoiceService voiceService;
    @Transactional
    public void createCartItem(CartItemCreateRequestDto requestDto) throws IllegalAccessException {
        //String id = (String) session.getAttribute("id");
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Cart cart = cartRepository.findByUserId(user);
        if(cart == null){
            throw new IllegalAccessException("장바구니가 없습니다.");
        }
        DinnerList dinner = dinnerRepository.findById(requestDto.getDinner()).orElseThrow(() -> new IllegalArgumentException("해당 디너가 없습니다."));
        StyleList style = styleRepository.findById(requestDto.getStyle()).orElseThrow(() -> new IllegalArgumentException("해당 스타일이 없습니다."));
        Long price = dinner.getPrice() + style.getPrice();
        cartItemRepository.save(requestDto.toEntity(cart, price, dinner, style));
    }

    @Transactional
    public void createCartItemWithVoice(CartItemVoiceCreateRequestDto requestDto, HttpSession session) throws IOException, InterruptedException, IllegalAccessException {
        MultipartFile audioFile = requestDto.getAudioFile();
        String token = (String) session.getAttribute("token");

        //String id = (String) session.getAttribute("id");
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Cart cart = cartRepository.findByUserId(user);
        if(cart == null){
            throw new IllegalAccessException("장바구니 생성을 못하였습니다.");
        }
        //System.out.println("token: "+token);
        // 음성 변환
        String voiceId = voiceService.generateId(audioFile, token);
        Thread.sleep(2000);
        DinnerStyleVoice dinnerStyle = voiceService.getMenu(voiceId, token);

        DinnerList dinner = dinnerRepository.findById(dinnerStyle.getDinner()).orElseThrow(() -> new IllegalArgumentException("해당 디너가 없습니다."));
        StyleList style = styleRepository.findById(dinnerStyle.getStyle()).orElseThrow(() -> new IllegalArgumentException("해당 스타일이 없습니다."));
        Long price = dinner.getPrice() + style.getPrice();
        cartItemRepository.save(requestDto.toEntity(cart, price, dinner, style));
    }

    @Transactional
    public List<CartItem> fetchCartItem(CartItemFetchRequestDto requestDto) {
        //String id = (String) session.getAttribute("id");
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Cart cart = cartRepository.findByUserId(user);
        if(cart == null){
            throw new IllegalArgumentException("장바구니가 없습니다.");
        }
        List<CartItem> cartItems = cartItemRepository.findByCartNo(cart);
        if(cart == null){
            throw new IllegalArgumentException("장바구니에 담긴 주문이 없습니다.");
        }
        return cartItems;

    }

    @Transactional
    public void updateCartItem(CartItemUpdateRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findById(requestDto.getCartItemNo()).orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다."));
        DinnerList dinnerList = dinnerRepository.findById(requestDto.getDinner()).orElseThrow(() -> new IllegalArgumentException("해당 디너가 없습니다."));
        StyleList styleList = styleRepository.findById(requestDto.getStyle()).orElseThrow(() -> new IllegalArgumentException("해당 스타일이 없습니다."));
        cartItem.update(dinnerList, styleList);
    }

    @Transactional
    public void deleteCartItem(CartItemDeleteRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findById(requestDto.getCartItemNo()).orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다."));
        cartDetailRepository.deleteAllByCartItemNo(cartItem);;

        cartItemRepository.delete(cartItem);
    }


}
