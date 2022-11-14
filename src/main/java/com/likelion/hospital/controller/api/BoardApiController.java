package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.board.BoardDTO;
import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.repository.BoardRepository;
import com.likelion.hospital.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/boards")
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO boardDTO) {
        log.info("author:{}, title:{}", boardDTO.getAuthor(), boardDTO.getTitle());
        return ResponseEntity.ok(boardService.create(boardDTO));
    }

    @GetMapping("")
    public ResponseEntity<Page<BoardDTO>> getAll(@PageableDefault Pageable pageable) {
        log.info("전체 리스트 요청 page:{}, pageSize:{}", pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(boardService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getOneById(@PathVariable("id") Long id) {
        log.info("게시글 조회 id:{}", id);
        return ResponseEntity.ok(boardService.getOneById(id));
    }
}
