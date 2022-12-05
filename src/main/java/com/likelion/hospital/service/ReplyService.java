package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;
import com.likelion.hospital.domain.entity.User;

import java.util.List;

public interface ReplyService {
    ReplyResDTO create(ReplyReqDTO replyReqDTO, User me) throws RuntimeException;

    List<ReplyResDTO> findByBoardId(Long id);
}
