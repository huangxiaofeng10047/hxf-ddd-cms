package com.hxf.dddexample.cms.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PublishState {
    TO_PUBLISH(0, "待发布"),
    PUBLISHED(1, "已发布"),
    ;
    private final Integer code;
    private final String msg;

    private static Map<Integer, PublishState> map = Arrays.stream(PublishState.values()).collect(Collectors.toMap(PublishState::getCode, s -> s));

    public static PublishState getByCode(Integer code) {
        return map.get(code);
    }
}
