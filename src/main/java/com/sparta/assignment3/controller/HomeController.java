package com.sparta.assignment3.controller;

import com.sparta.assignment3.dto.MemoRequestDto;
import com.sparta.assignment3.security.UserDetailsImpl;
import com.sparta.assignment3.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MemoService memoService;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("userId", userDetails.getUser().getId());
        return "index";
    }

    @GetMapping("/memos/commentPage")
    public String getBoardContent(Model model, @RequestParam("memoid") Long memoid) throws Exception {
        model.addAttribute("commentsMemo", memoService.getMemos(memoid));
        return "commentPage";
    }
}