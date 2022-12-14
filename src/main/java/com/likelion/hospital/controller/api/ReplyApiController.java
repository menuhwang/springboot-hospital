package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;
import com.likelion.hospital.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/replies")
public class ReplyApiController {
    private final ReplyService replyService;

    @GetMapping("")
    public ResponseEntity<List<ReplyResDTO>> getRepliesByBoardId(@RequestParam("board") Long id) {
        return ResponseEntity.ok(replyService.findByBoardId(id));
    }

    @PostMapping("")
    public ResponseEntity<ReplyResDTO> create(@RequestBody ReplyReqDTO replyReqDTO, Principal me) {
        log.info("λκΈ μμ± {}", replyReqDTO);
        log.info("User {}", me.getName());
        return ResponseEntity.ok(replyService.create(replyReqDTO, me));
    }
}
