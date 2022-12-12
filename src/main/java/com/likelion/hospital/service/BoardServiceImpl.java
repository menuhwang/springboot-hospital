package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardResWithReplyDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.exception.forbidden.PlainForbiddenException;
import com.likelion.hospital.exception.notfound.BoardNotFoundException;
import com.likelion.hospital.exception.notfound.UserNotFoundException;
import com.likelion.hospital.repository.BoardRepository;
import com.likelion.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public BoardResDTO create(BoardReqDTO boardDTO, Principal me) {
        User user = userRepository.findByUsername(me.getName()).orElseThrow(UserNotFoundException::new);
        return BoardResDTO.of(boardRepository.save(boardDTO.toEntity(user)));
    }

    @Override
    public Page<BoardResDTO> getAll(Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);
        return page.map(BoardResDTO::of);
    }

    @Override
    public BoardResWithReplyDTO getById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        return BoardResWithReplyDTO.of(board);
    }

    @Override
    @Transactional // 더티 체킹
    public BoardResDTO editById(Long id, BoardReqDTO boardReqDTO, Principal me) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        if (board.getAuthor().getUsername() != me.getName()) throw new PlainForbiddenException();
        board.updateTitle(boardReqDTO.getTitle());
        board.updateContent(boardReqDTO.getContent());
        return BoardResDTO.of(board);
    }

    @Override
    public void deleteById(Long id, Principal me) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        User user = userRepository.findByUsername(me.getName()).orElseThrow(UserNotFoundException::new);
        if (board.getAuthor().getId() != user.getId()) throw new PlainForbiddenException();
        boardRepository.deleteById(id);
    }
}
