package com.hxf.dddexample.cms.service.dto;

import com.hxf.dddexample.cms.domain.ArticleEntity;
import org.springframework.stereotype.Component;

@Component
public class ArticleViewAssembler {

    public ArticleView assembler(ArticleEntity entity) {

        ArticleView view = new ArticleView();
        view.setArticleId(entity.getArticleId().getValue());
        view.setTitle(entity.getTitle().getValue());
        view.setContent(entity.getContent().getValue());
        return view;
    }
}
