package com.likelion.hospital.exception.notfound;

public class BoardNotFoundException extends AbstractNotFoundException {
    public BoardNotFoundException() {
        super("해당 게시물을 찾을 수 없습니다.");
    }
}
