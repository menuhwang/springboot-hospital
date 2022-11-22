package com.likelion.hospital.repository;

import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByBoard(Board board);
}
