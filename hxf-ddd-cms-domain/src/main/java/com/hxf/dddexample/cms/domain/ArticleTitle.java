package com.hxf.dddexample.cms.domain;

import java.util.Objects;

import com.feiniaojin.ddd.ValueObject;

public class ArticleTitle implements ValueObject<String> {

    private final String value;


    public ArticleTitle(String value) {
        this.check(value);
        this.value = value;
    }

    private void check(String value) {
        Objects.requireNonNull(value, "标题不能为空");
        if (value.length() > 64) {
            throw new IllegalArgumentException("标题过长");
        }
    }

    @Override
    public String getValue() {
        return this.value;
    }
    
}
