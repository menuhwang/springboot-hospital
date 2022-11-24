package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardResWithReplyDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardResDTO create(BoardReqDTO boardDTO) {
        return BoardResDTO.of(boardRepository.save(boardDTO.toEntity()));
    }

    @Override
    public Page<BoardResDTO> getAll(Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);
        return page.map(BoardResDTO::of);
    }

    @Override
    public BoardResWithReplyDTO getById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을 찾을 수 없습니다."));
        return BoardResWithReplyDTO.of(board);
    }

    @Override
    @Transactional // 더티 체킹
    public BoardResDTO editById(Long id, BoardReqDTO boardReqDTO) {
        Board board= boardRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을 찾을 수 없습니다."));
        board.updateAuthor(boardReqDTO.getAuthor());
        board.updateTitle(boardReqDTO.getTitle());
        board.updateContent(boardReqDTO.getContent());
        return BoardResDTO.of(board);
    }

    @Override
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
