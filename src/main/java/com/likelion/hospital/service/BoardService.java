package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardResWithReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface BoardService {
    BoardResDTO create(BoardReqDTO boardReqDTO, Principal me);

    Page<BoardResDTO> getAll(Pageable pageable);

    BoardResWithReplyDTO getById(Long id);

    BoardResDTO editById(Long id, BoardReqDTO boardReqDTO, Principal me);

    void deleteById(Long id, Principal me);
}
