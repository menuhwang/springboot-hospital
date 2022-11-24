package com.likelion.hospital.domain.dto.board;

import com.likelion.hospital.domain.dto.reply.ReplyResDTO;
import com.likelion.hospital.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class BoardResWithReplyDTO {
    private Long id;
    private String author;
    private String title;
    private String content;
    private List<ReplyResDTO> reply;

    public static BoardResWithReplyDTO of(Board board) {
        return BoardResWithReplyDTO.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .reply(board.getReplies().stream().map(ReplyResDTO::of).collect(Collectors.toList()))
                .build();
    }
}
