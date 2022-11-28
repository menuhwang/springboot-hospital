package com.likelion.hospital.exception;

public class UserNotFoundException extends AbstractNotFoundException {
    public UserNotFoundException() {
        super("해당 유저를 찾을 수 없습니다.");
    }
}
