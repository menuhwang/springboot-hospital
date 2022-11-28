package com.likelion.hospital.exception.forbidden;

public class SignInForbiddenException extends AbstractForbiddenException {
    public SignInForbiddenException() {
        super("아이디 또는 비밀번호를 확인해주세요.");
    }
}
