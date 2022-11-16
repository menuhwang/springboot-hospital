package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardReqDTO;
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
    public ResponseEntity<BoardResDTO> create(@RequestBody BoardReqDTO boardReqDTO) {
        log.info("author:{}, title:{}", boardReqDTO.getAuthor(), boardReqDTO.getTitle());
        return ResponseEntity.ok(boardService.create(boardReqDTO));
    }

    @GetMapping("")
    public ResponseEntity<Page<BoardResDTO>> getAll(@PageableDefault Pageable pageable) {
        log.info("전체 리스트 요청 page:{}, pageSize:{}", pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(boardService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResDTO> getOneById(@PathVariable("id") Long id) {
        log.info("게시글 조회 id:{}", id);
        return ResponseEntity.ok(boardService.getOneById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BoardResDTO> editOneById(@PathVariable("id") Long id, @RequestBody BoardReqDTO boardReqDTO) {
        log.info("게시글 수정 id:{}, body:{}", id, boardReqDTO);
        return ResponseEntity.ok(boardService.editOneById(id, boardReqDTO));
    }
}
