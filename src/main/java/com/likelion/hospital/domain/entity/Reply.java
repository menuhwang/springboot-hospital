package com.likelion.hospital.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private User author;
    @Column(nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }
}
