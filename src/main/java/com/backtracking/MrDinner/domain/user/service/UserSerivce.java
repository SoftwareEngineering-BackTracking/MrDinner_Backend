package com.backtracking.MrDinner.domain.user.service;

import com.backtracking.MrDinner.domain.cart.repository.CartRepository;
import com.backtracking.MrDinner.domain.cart.service.CartService;
import com.backtracking.MrDinner.domain.user.dto.UserCreateRequestDto;
import com.backtracking.MrDinner.domain.user.dto.UserDeleteRequestDto;
import com.backtracking.MrDinner.domain.user.dto.UserFetchRequestDto;
import com.backtracking.MrDinner.domain.user.dto.UserUpdateRequestDto;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import com.backtracking.MrDinner.domain.user.repository.UserSpecification;
import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserSerivce {
    private Department department;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    // 회원가입
    @Transactional
    public void createUser(UserCreateRequestDto requestDto) {
        String email = requestDto.getEmail();
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("이미 가입되어 있는 유저입니다. EMAIL: " + email);
        }
        userRepository.save(requestDto.toEntity());
        if(!cartRepository.existsByUserId(requestDto.getId())){
            cartService.createCart(requestDto.getId());
        }
    }

    // 내 정보 보기
    @Transactional
    public User fetchUser(UserFetchRequestDto requestDto) {
        String id = requestDto.getId();
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 유저가 없습니다."));
    }

    // 특정 유저 정보 보기
    @Transactional
    public List<User> fetchFilterUser(UserFetchRequestDto requestDto) {
        Specification<User> spec = (root, query, criteriaBuilder) -> null;

        if(requestDto.getName() != null){
            spec = spec.and(UserSpecification.containingUserName(requestDto.getName()));
        }
        if(requestDto.getDepartment() == department.고객 || requestDto.getDepartment() == department.직원  || requestDto.getDepartment() == department.비회원) {
            spec = spec.and(UserSpecification.equalDepartment(requestDto.getDepartment()));
        }
        List<User> userList = userRepository.findAll(spec);

        if(userList.isEmpty()){
            throw new IllegalArgumentException("해당 유저가 없습니다.");
        }
        else {
            return userList;
        }
    }

    // 모든 유저 정보 보기
    @Transactional
    public List<User> fetchAllUser(UserFetchRequestDto requestDto) {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    // 내 정보 수정
    @Transactional
    public void updateUser(UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 유저가 없습니다."));

        user.update(requestDto.getName(), requestDto.getBirth(), requestDto.getPhoneNumber(), requestDto.getEmail(), requestDto.getNickname());
    }

//    @Transactional
//    public void updateAddress(UserUpdateRequestDto requestDto, HttpSession session) {
//        User user = userRepository.findById((String) session.getAttribute("id")).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 유저가 없습니다."));
//
//        user.setAddress(requestDto.getAddress());
//    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(UserDeleteRequestDto requestDto){
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 유저가 없숩니다."));

        userRepository.delete(user);
    }

}
