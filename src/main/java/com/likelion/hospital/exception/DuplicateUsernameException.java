package com.likelion.hospital.exception;

public class DuplicateUsernameException extends AbstractBadRequestException {
    public DuplicateUsernameException() {
        super("이미 존재하는 아이디입니다.");
    }
}
