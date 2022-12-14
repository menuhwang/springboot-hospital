package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;

import java.security.Principal;
import java.util.List;

public interface ReplyService {
    ReplyResDTO create(ReplyReqDTO replyReqDTO, Principal me) throws RuntimeException;

    List<ReplyResDTO> findByBoardId(Long id);
}
