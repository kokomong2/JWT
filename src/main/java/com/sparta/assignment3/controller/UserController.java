package com.sparta.assignment3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.sparta.assignment3.dto.SignupRequestDto;
import com.sparta.assignment3.dto.UserInfoDto;
import com.sparta.assignment3.model.Memo;
import com.sparta.assignment3.model.User;
import com.sparta.assignment3.repository.UserRepository;
import com.sparta.assignment3.security.UserDetailsImpl;
import com.sparta.assignment3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository=userRepository;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }
    //전체 유저 조회
    @ResponseBody
    @GetMapping("/user/all")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        return new UserInfoDto(username);
    }
}