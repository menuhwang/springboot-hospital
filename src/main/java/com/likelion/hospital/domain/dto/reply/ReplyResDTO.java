package com.likelion.hospital.domain.dto.reply;

import com.likelion.hospital.domain.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReplyResDTO {
    private Long id;
    private String author;
    private String content;

    public static ReplyResDTO from(Reply reply) {
        return ReplyResDTO.builder()
                .id(reply.getId())
                .author(reply.getAuthor())
                .content(reply.getContent())
                .build();
    }
}
