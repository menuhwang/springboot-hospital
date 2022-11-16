package com.likelion.hospital.domain.dto.board;

import com.likelion.hospital.domain.entity.Board;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResDTO {
    private Long id;
    private String author;
    private String title;
    private String content;

    public static BoardResDTO from(Board entity) {
        return new BoardResDTO(entity.getId(), entity.getAuthor(), entity.getTitle(), entity.getContent());
    }
}
