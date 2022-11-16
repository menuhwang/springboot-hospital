package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardResDTO create(BoardReqDTO boardDTO) {
        return BoardResDTO.from(boardRepository.save(boardDTO.toEntity()));
    }

    @Override
    public Page<BoardResDTO> getAll(Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);
        return page.map(BoardResDTO::from);
    }

    @Override
    public BoardResDTO getOneById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을 찾을 수 없습니다."));
        return BoardResDTO.from(board);
    }
}
