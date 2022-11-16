package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class BoardServiceImplTest {
    private BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private BoardService boardService = new BoardServiceImpl(boardRepository);

    @Test
    void create() {
        BoardDTO boardDTO = new BoardDTO(1L, "author", "title", "content");
        Board board = boardDTO.toEntity();

        given(boardRepository.save(any(Board.class))).willReturn(board);

        BoardDTO result = boardService.create(boardDTO);

        assertBoardDTO(boardDTO, result);
    }

    @Test
    void getOneById() {
    }

    void assertBoardDTO(BoardDTO expected, BoardDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
    }
}