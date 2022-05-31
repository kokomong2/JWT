package com.sparta.assignment3.controller;


import com.sparta.assignment3.dto.CommentRequestDto;
import com.sparta.assignment3.model.Comment;
import com.sparta.assignment3.repository.CommentRepository;
import com.sparta.assignment3.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;


    @PostMapping("/api/memos/comments")
    public Comment postComments(@RequestBody CommentRequestDto requestDto,
                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        Comment comment = new Comment(requestDto, userId);
        return commentRepository.save(comment);
    }

    @GetMapping("/comments/all")
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }
}
