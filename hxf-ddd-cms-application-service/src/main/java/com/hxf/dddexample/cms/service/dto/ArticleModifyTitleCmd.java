package com.hxf.dddexample.cms.service.dto;

import lombok.Data;

@Data
public class ArticleModifyTitleCmd {
    private String articleId;
    private String title;
}
