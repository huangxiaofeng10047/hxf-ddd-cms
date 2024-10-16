package com.hxf.dddexample.cms.service.dto;

import lombok.Data;

@Data
public class ArticleModifyContentCmd {
    private String articleId;
    private String content;
}
