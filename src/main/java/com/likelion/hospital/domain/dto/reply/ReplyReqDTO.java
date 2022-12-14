package com.likelion.hospital.domain.dto.reply;

import com.likelion.hospital.domain.entity.Board;
import com.likelion.hospital.domain.entity.Reply;
import com.likelion.hospital.domain.entity.User;
import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor // 스웨거 바인딩 이슈 때문에 추가.
@Getter
public class ReplyReqDTO {
    private String content;
    private Long boardId;

    public Reply toEntity(Board board, User author) {
        return Reply.builder()
                .author(author)
                .board(board)
                .content(content)
                .build();
    }
}
