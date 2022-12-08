package com.sparta.post.controller;

import com.sparta.post.dto.LoginRequestDto;
import com.sparta.post.dto.ResponseDto;
import com.sparta.post.dto.SignupRequestDto;
import com.sparta.post.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
       userService.signup(signupRequestDto);
       return "success";

    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return "success";
    }

}