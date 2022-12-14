package com.likelion.hospital.domain.dto.board;

import com.likelion.hospital.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResDTO {
    private Long id;
    private String author;
    private String title;
    private String content;

    public static BoardResDTO of(Board entity) {
        return BoardResDTO.builder()
                .id(entity.getId())
                .author(entity.getAuthor().getUsername())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }
}
