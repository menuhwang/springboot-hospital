package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.domain.entity.Reply;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.exception.notfound.BoardNotFoundException;
import com.likelion.hospital.repository.BoardRepository;
import com.likelion.hospital.repository.ReplyRepository;
import com.likelion.hospital.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class ReplyServiceImplTest {
    private final BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private final ReplyRepository replyRepository = Mockito.mock(ReplyRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final ReplyService replyService = new ReplyServiceImpl(boardRepository, replyRepository, userRepository);

    private final User user = User.builder()
            .id(1L)
            .username("tester")
            .email("tester@test.com")
            .build();

    private final Principal principal = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

    @Test
    void create() {
        // given
        ReplyReqDTO replyReqDTO = ReplyReqDTO.builder()
                .content("content")
                .boardId(1L)
                .build();

        Board board = Board.builder()
                .id(1L)
                .author(user)
                .title("title")
                .content("content")
                .build();

        Reply saved = Reply.builder()
                .id(1L)
                .author(user)
                .content("content")
                .build();

        given(userRepository.findByUsername(principal.getName())).willReturn(Optional.of(user));
        given(boardRepository.findById(anyLong())).willReturn(Optional.ofNullable(board));
        given(replyRepository.save(any(Reply.class))).willReturn(saved);

        // when
        ReplyResDTO replyResDTO = replyService.create(replyReqDTO, principal);

        // then
        assertReply(replyReqDTO, replyResDTO);
    }

    @Test
    void createWhenNoBoard() {
        // given
        ReplyReqDTO replyReqDTO = ReplyReqDTO.builder()
                .content("content")
                .boardId(1L)
                .build();

        given(boardRepository.findById(anyLong())).willReturn(Optional.empty());

        // when then
        assertThrows(RuntimeException.class, () -> replyService.create(replyReqDTO, new UsernamePasswordAuthenticationToken(user, null, null)));
        // any()는 mocking 할때만 사용할 것!
    }

    @Test
    void findByBoardId_게시글이_없는_경우_BoardNotFoundException() {
        given(boardRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(BoardNotFoundException.class, () -> replyService.findByBoardId(0L));
    }

    private void assertReply(ReplyReqDTO expected, ReplyResDTO actual) {
        assertNotNull(actual.getId());
        assertEquals(expected.getContent(), actual.getContent());
    }
}