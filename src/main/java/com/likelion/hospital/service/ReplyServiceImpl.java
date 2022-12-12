package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.domain.entity.Reply;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.exception.notfound.BoardNotFoundException;
import com.likelion.hospital.exception.notfound.UserNotFoundException;
import com.likelion.hospital.repository.BoardRepository;
import com.likelion.hospital.repository.ReplyRepository;
import com.likelion.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ReplyResDTO create(ReplyReqDTO replyReqDTO, Principal me) throws RuntimeException {
        User user = userRepository.findByUsername(me.getName()).orElseThrow(UserNotFoundException::new);
        Board board = boardRepository.findById(replyReqDTO.getBoardId()).orElseThrow(BoardNotFoundException::new);
        Reply reply = replyReqDTO.toEntity(board, user);
        Reply saved = replyRepository.save(reply);
        return ReplyResDTO.of(saved);
    }

    @Override
    public List<ReplyResDTO> findByBoardId(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        List<Reply> replies = replyRepository.findByBoard(board);
        return replies.stream().map(ReplyResDTO::of).collect(Collectors.toList());
    }
}
