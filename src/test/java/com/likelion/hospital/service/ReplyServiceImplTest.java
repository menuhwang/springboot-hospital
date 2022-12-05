package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.domain.entity.Reply;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.repository.BoardRepository;
import com.likelion.hospital.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class ReplyServiceImplTest {
    private final BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private final ReplyRepository replyRepository = Mockito.mock(ReplyRepository.class);

    private final ReplyService replyService = new ReplyServiceImpl(boardRepository, replyRepository);

    User user = User.builder()
            .id(1L)
            .username("tester")
            .email("tester@test.com")
            .build();

    @Test
    void create() {
        // given
        ReplyReqDTO replyReqDTO = ReplyReqDTO.builder()
                .author("author")
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
                .author("author")
                .content("content")
                .build();

        given(boardRepository.findById(anyLong())).willReturn(Optional.ofNullable(board));
        given(replyRepository.save(any(Reply.class))).willReturn(saved);

        // when
        ReplyResDTO replyResDTO = replyService.create(replyReqDTO);

        // then
        assertReply(replyReqDTO, replyResDTO);
    }

    @Test
    void createWhenNoBoard() {
        // given
        ReplyReqDTO replyReqDTO = ReplyReqDTO.builder()
                .author("author")
                .content("content")
                .boardId(1L)
                .build();

        given(boardRepository.findById(anyLong())).willReturn(Optional.empty());

        // when then
        assertThrows(RuntimeException.class, () -> replyService.create(replyReqDTO));
        // any()는 mocking 할때만 사용할 것!
    }

    private void assertReply(ReplyReqDTO expected, ReplyResDTO actual) {
        assertNotNull(actual.getId());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getContent(), actual.getContent());
    }
}