package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;

public interface ReplyService {
    ReplyResDTO create(ReplyReqDTO replyReqDTO) throws RuntimeException;
}
