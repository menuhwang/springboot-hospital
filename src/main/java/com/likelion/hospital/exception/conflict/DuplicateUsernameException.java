package com.likelion.hospital.exception.conflict;

public class DuplicateUsernameException extends AbstractConflictException {
    public DuplicateUsernameException() {
        super("이미 존재하는 아이디입니다.");
    }
}
