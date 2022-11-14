package com.likelion.hospital.domain.dto.board;

import com.likelion.hospital.domain.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class BoardDTO {
    private Long id;
    private String author;
    private String title;
    private String content;

    public BoardDTO(Long id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Board toEntity() {
        return new Board(id, author, title, content);
    }

    public static BoardDTO from(Board entity) {
        return new BoardDTO(entity.getId(), entity.getAuthor(), entity.getTitle(), entity.getContent());
    }
}
