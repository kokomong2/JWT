package com.sparta.assignment3.controller;

import com.sparta.assignment3.dto.MemoRequestDto;
import com.sparta.assignment3.model.Memo;
import com.sparta.assignment3.model.User;
import com.sparta.assignment3.security.UserDetailsImpl;
import com.sparta.assignment3.service.MemoService;
import com.sparta.assignment3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MemoService memoService;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null){
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("userId", userDetails.getUser().getId());
        }
        return "index";
    }

    @GetMapping("/memos/commentPage")
    public String getBoardContent(Model model, @RequestParam("memoid") Long memoid, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        model.addAttribute("username",userDetails.getUsername());
        model.addAttribute("commentsMemo", memoService.getMemos(memoid));
        Long userid = memoService.getMemos(memoid).getUserId();
        User user = userService.getUserDetail(userid);
        model.addAttribute("memo_writername",user.getUsername());
        return "commentPage";
    }
}