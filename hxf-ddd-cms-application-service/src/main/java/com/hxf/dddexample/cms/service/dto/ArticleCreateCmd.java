package com.hxf.dddexample.cms.service.dto;

import lombok.Data;

@Data
public class ArticleCreateCmd {
    private String title;
    private String content;
}
