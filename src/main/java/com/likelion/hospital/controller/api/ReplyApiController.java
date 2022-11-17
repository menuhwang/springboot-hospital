package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;
import com.likelion.hospital.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/replies")
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping("")
    public ResponseEntity<ReplyResDTO> create(@RequestBody ReplyReqDTO replyReqDTO) {
        return ResponseEntity.ok(replyService.create(replyReqDTO));
    }
}
