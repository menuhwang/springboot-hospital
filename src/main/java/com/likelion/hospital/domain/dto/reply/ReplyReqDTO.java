package com.likelion.hospital.domain.dto.reply;

import com.likelion.hospital.domain.entity.Reply;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReplyReqDTO {
    private String author;
    private String content;
    private Long boardId;

    public Reply toEntity() {
        return Reply.builder()
                .author(author)
                .content(content)
                .build();
    }
}
