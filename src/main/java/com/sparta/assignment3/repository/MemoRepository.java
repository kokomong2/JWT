package com.sparta.assignment3.repository;

import com.sparta.assignment3.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start,LocalDateTime end);
    List<Memo> findAllByOrderByModifiedAtDesc();
    List<Memo> findAllById(Long userId);
}
