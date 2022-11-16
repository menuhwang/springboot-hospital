package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class BoardServiceImplTest {
    private final BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private final BoardService boardService = new BoardServiceImpl(boardRepository);

    BoardReqDTO boardReqDTO = BoardReqDTO.builder()
            .author("author")
            .title("title")
            .content("content")
            .build();

    @Test
    void create() {
        Board board = boardReqDTO.toEntity();

        given(boardRepository.save(any(Board.class))).willReturn(board);

        BoardResDTO result = boardService.create(boardReqDTO);

        assertBoardDTO(boardReqDTO, result);
    }

    @Test
    void getOneById() {
        given(boardRepository.findById(1L)).willReturn(Optional.of(boardReqDTO.toEntity()));

        BoardResDTO result = boardService.getOneById(1L);

        assertBoardDTO(boardReqDTO, result);
    }

    @Test
    void editById() {
        given(boardRepository.findById(1L)).willReturn(Optional.of(boardReqDTO.toEntity()));

        BoardReqDTO editDTO = BoardReqDTO.builder()
                                            .author("edit-author")
                                            .title("edit-title")
                                            .content("edit-content")
                                            .build();

        BoardResDTO result = boardService.editOneById(1L, editDTO);

        assertBoardDTO(editDTO, result);
    }

    void assertBoardDTO(BoardReqDTO expected, BoardResDTO actual) {
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
    }
}