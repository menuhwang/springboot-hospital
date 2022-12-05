package com.likelion.hospital.exception.forbidden;

public class PlainForbiddenException extends AbstractForbiddenException {
    public PlainForbiddenException() {
        super("접근 권한이 없습니다.");
    }
}
