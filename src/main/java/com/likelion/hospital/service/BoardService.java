package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardResWithReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardResDTO create(BoardReqDTO boardReqDTO);

    Page<BoardResDTO> getAll(Pageable pageable);

    BoardResWithReplyDTO getById(Long id);

    BoardResDTO editById(Long id, BoardReqDTO boardReqDTO);
    void deleteById(Long id);
}
