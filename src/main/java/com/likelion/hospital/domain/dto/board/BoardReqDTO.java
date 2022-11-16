package com.likelion.hospital.domain.dto.board;

import com.likelion.hospital.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BoardReqDTO {
    private String author;
    private String title;
    private String content;

    public Board toEntity() {
        return Board.builder()
                .author(author)
                .title(title)
                .content(content)
                .build();
    }
}
