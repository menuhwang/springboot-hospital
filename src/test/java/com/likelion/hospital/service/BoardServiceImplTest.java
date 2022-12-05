package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardResWithReplyDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.repository.BoardRepository;
import com.likelion.hospital.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class BoardServiceImplTest {
    private final BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final BoardService boardService = new BoardServiceImpl(boardRepository, userRepository);

    User user = User.builder()
            .id(1L)
            .username("tester")
            .email("tester@test.com")
            .build();

    BoardReqDTO boardReqDTO = BoardReqDTO.builder()
            .title("title")
            .content("content")
            .build();

    @Test
    void create() {
        Board board = boardReqDTO.toEntity(user);

        given(boardRepository.save(any(Board.class))).willReturn(board);
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));

        BoardResDTO result = boardService.create(boardReqDTO, user);

        assertBoardDTO(boardReqDTO, result);
    }

    @Test
    void getOneById() {
        given(boardRepository.findById(1L)).willReturn(Optional.of(boardReqDTO.toEntity(user)));

        BoardResWithReplyDTO result = boardService.getById(1L);

        assertBoardDTO(boardReqDTO, result);
    }

    @Test
    void getAll() {
        List<Board> boardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            boardList.add(Board.builder()
                    .author(user)
                    .title("title"+i)
                    .content("content"+i)
                    .build());
        }
        Page<Board> page = new PageImpl<>(boardList);

        given(boardRepository.findAll(any(Pageable.class))).willReturn(page);

        Page<BoardResDTO> result = boardService.getAll(PageRequest.of(0, 10));

        assertPage(page, result);
    }

    @Test
    void editById() {
        given(boardRepository.findById(1L)).willReturn(Optional.of(boardReqDTO.toEntity(new User())));

        BoardReqDTO editDTO = BoardReqDTO.builder()
                                            .title("edit-title")
                                            .content("edit-content")
                                            .build();

        BoardResDTO result = boardService.editById(1L, editDTO);

        assertBoardDTO(editDTO, result);
    }

    private void assertPage(Page<Board> expected, Page<BoardResDTO> actual) {
        assertEquals(expected.getTotalPages(), expected.getTotalPages());
        assertEquals(expected.getNumber(), actual.getNumber());
        assertEquals(expected.getSize(), actual.getSize());
        List<Board> expectedContent = expected.getContent();
        List<BoardResDTO> actualContent = actual.getContent();
        for (int i = 0; i < expected.getSize(); i++) {
            assertBoard(expectedContent.get(i), actualContent.get(i));
        }
    }

    void assertBoardDTO(BoardReqDTO expected, BoardResDTO actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
    }

    void assertBoardDTO(BoardReqDTO expected, BoardResWithReplyDTO actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
    }

    void assertBoard(Board expected, BoardResDTO actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
    }
}