package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardDTO create(BoardDTO boardDTO);
    Page<BoardDTO> getAll(Pageable pageable);
}
