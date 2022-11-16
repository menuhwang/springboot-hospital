package com.likelion.hospital.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String author;
    @Column
    private String title;
    @Column(nullable = false)
    private String content;

    public void updateAuthor(String author) {
        if (author != null) this.author = author;
    }

    public void updateTitle(String title) {
        if (title != null) this.title = title;
    }

    public void updateContent(String content) {
        if (content != null) this.content = content;
    }
}
