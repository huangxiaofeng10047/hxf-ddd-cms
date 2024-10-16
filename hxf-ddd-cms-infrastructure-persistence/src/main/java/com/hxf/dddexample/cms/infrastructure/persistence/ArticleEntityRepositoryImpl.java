package com.hxf.dddexample.cms.infrastructure.persistence;


import com.feiniaojin.ddd.EntityId;
import com.hxf.dddexample.cms.domain.ArticleContent;
import com.hxf.dddexample.cms.domain.ArticleEntity;
import com.hxf.dddexample.cms.domain.ArticleEntityRepository;
import com.hxf.dddexample.cms.domain.PublishState;
import com.hxf.dddexample.cms.domain.ArticleId;
import com.hxf.dddexample.cms.domain.ArticleTitle;
import com.hxf.dddexample.cms.infrastructure.persistence.data.CmsArticle;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ArticleEntityRepositoryImpl implements ArticleEntityRepository {


    @Resource
    private ArticleJdbcRepository jdbcRepository;
    @Override
    public ArticleEntity load(ArticleId entityId) {

        CmsArticle article = jdbcRepository.queryOne(entityId.getValue());

        ArticleEntity entity = new ArticleEntity();

        entity.setTitle(new ArticleTitle(article.getTitle()));
        entity.setPublishState(PublishState.getByCode(article.getPublishState()).getCode());
        entity.setArticleId(new ArticleId(article.getArticleId()));
        entity.setContent(new ArticleContent(article.getContent()));

        entity.setVersion(article.getVersion());
        entity.setId(article.getId());
        entity.setCreatedBy(article.getCreatedBy());
        entity.setCreatedTime(article.getCreatedTime());
        entity.setModifiedTime(article.getModifiedTime());
        entity.setModifiedBy(article.getModifiedBy());
        entity.setDeleted(article.getDeleted());

        return entity;
    }


    @Override
    @Transactional
    public void save(ArticleEntity entity) {

        CmsArticle cmsArticle = new CmsArticle();
        cmsArticle.setTitle(entity.getTitle().getValue());
        cmsArticle.setContent(entity.getContent().getValue());
        cmsArticle.setArticleId(entity.getArticleId().getValue());
        cmsArticle.setPublishState(entity.getPublishState());

        cmsArticle.setId(entity.getId());
        cmsArticle.setCreatedBy(entity.getCreatedBy());
        cmsArticle.setCreatedTime(entity.getCreatedTime());
        cmsArticle.setModifiedBy(entity.getModifiedBy());
        cmsArticle.setModifiedTime(entity.getModifiedTime());
        cmsArticle.setVersion(entity.getVersion());
        cmsArticle.setDeleted(entity.getDeleted());

        jdbcRepository.save(cmsArticle);
    }
}
