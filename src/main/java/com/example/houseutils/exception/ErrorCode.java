package com.example.houseutils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 에러 메시지에 대해 Enum으로 관리하기!
 */
@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_REQUEST("잘못된 요청입니다."),
    INVALID_ERROR("알수 없는 에러가 발생했습니다."),
    ENTITY_NOTFOUND("데이터를 찾을 수 없습니다.");

    private final String message;
}
