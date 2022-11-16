package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardResDTO create(BoardReqDTO boardReqDTO);
    Page<BoardResDTO> getAll(Pageable pageable);
    BoardResDTO getOneById(Long id);
}
