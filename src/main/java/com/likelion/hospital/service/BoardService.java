package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardResWithReplyDTO;
import com.likelion.hospital.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardResDTO create(BoardReqDTO boardReqDTO, User me);

    Page<BoardResDTO> getAll(Pageable pageable);

    BoardResWithReplyDTO getById(Long id);

    BoardResDTO editById(Long id, BoardReqDTO boardReqDTO);

    void deleteById(Long id, User me);
}
