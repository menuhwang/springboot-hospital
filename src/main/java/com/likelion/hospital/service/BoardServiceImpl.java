package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardDTO;
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
    public BoardDTO create(BoardDTO boardDTO) {
        return BoardDTO.from(boardRepository.save(boardDTO.toEntity()));
    }

    @Override
    public Page<BoardDTO> getAll(Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);
        return page.map(BoardDTO::from);
    }
}
