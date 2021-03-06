package com.sparta.assignment3.service;


import com.sparta.assignment3.model.Memo;
import com.sparta.assignment3.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public Memo getMemos(Long memoid){
        return memoRepository.findById(memoid).orElseThrow(()->new NullPointerException("해당 게시글이 없습니다."));
    }
}
