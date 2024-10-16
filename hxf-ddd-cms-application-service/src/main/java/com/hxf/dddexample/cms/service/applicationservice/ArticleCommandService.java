package com.hxf.dddexample.cms.service.applicationservice;



import com.hxf.dddexample.cms.domain.ArticleContent;
import com.hxf.dddexample.cms.domain.ArticleEntity;
import com.hxf.dddexample.cms.domain.ArticleEntityRepository;
import com.hxf.dddexample.cms.domain.ArticleFactory;
import com.hxf.dddexample.cms.domain.ArticleId;
import com.hxf.dddexample.cms.domain.ArticleTitle;
import com.hxf.dddexample.cms.service.dto.ArticleCreateCmd;
import com.hxf.dddexample.cms.service.dto.ArticleModifyContentCmd;
import com.hxf.dddexample.cms.service.dto.ArticleModifyTitleCmd;
import com.hxf.dddexample.cms.service.dto.ArticlePublishCmd;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleCommandService {

    @Resource
    private ArticleFactory factory;

    @Resource
    private ArticleEntityRepository repository;

    /**
     * 创建草稿
     *
     * @param cmd
     */
    public void newDraft(ArticleCreateCmd cmd) {
        ArticleEntity articleEntity = factory.newInstance(new ArticleTitle(cmd.getTitle()),
                new ArticleContent(cmd.getContent()));
        articleEntity.createDraft();
        repository.save(articleEntity);
    }

    /**
     * 发布草稿
     *
     * @param cmd
     */
    public void publishArticle(ArticlePublishCmd cmd) {
        ArticleEntity entity = repository.load(new ArticleId(cmd.getArticleId()));
        entity.publishArticle();
        repository.save(entity);
    }


    /**
     * 修改标题
     *
     * @param cmd
     */
    public void modifyTitle(ArticleModifyTitleCmd cmd) {
        ArticleEntity entity = repository.load(new ArticleId(cmd.getArticleId()));
        entity.modifyTitle(new ArticleTitle(cmd.getTitle()));
        repository.save(entity);
    }

    /**
     * 修改正文
     *
     * @param cmd
     */
    public void modifyContent(ArticleModifyContentCmd cmd) {
        ArticleEntity entity = repository.load(new ArticleId(cmd.getArticleId()));
        entity.modifyContent(new ArticleContent(cmd.getContent()));
        repository.save(entity);
    }

}
