package com.sparta.assignment3.service;

import com.sparta.assignment3.model.Comment;
import com.sparta.assignment3.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

}
