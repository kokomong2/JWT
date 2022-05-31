package com.sparta.assignment3.controller;


import com.sparta.assignment3.model.Memo;
import com.sparta.assignment3.model.User;
import com.sparta.assignment3.repository.MemoRepository;
import com.sparta.assignment3.dto.MemoRequestDto;
import com.sparta.assignment3.repository.UserRepository;
import com.sparta.assignment3.security.UserDetailsImpl;
import com.sparta.assignment3.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {
    private final MemoService memoService;
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    @GetMapping("/api/memos")
    public List<Memo> getMemos(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("/api/memos")
    public Memo postMemos(@RequestBody MemoRequestDto requestDto,
                          @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        Memo memo = new Memo(requestDto, userId);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos/username/{id}")
    public String getUsername(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(()->new NullPointerException("아이디가 없습니다."));
        return user.getUsername();
    }
}
