package com.hxf.dddexample.cms.service.applicationservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;


import com.feiniaojin.gracefulresponse.data.PageBean;
import com.hxf.dddexample.cms.domain.ArticleEntity;
import com.hxf.dddexample.cms.domain.ArticleEntityRepository;
import com.hxf.dddexample.cms.domain.ArticleId;
import com.hxf.dddexample.cms.domain.PublishState;
import com.hxf.dddexample.cms.infrastructure.persistence.ArticleJdbcRepository;
import com.hxf.dddexample.cms.infrastructure.persistence.data.CmsArticle;
import com.hxf.dddexample.cms.service.dto.ArticlePageQuery;
import com.hxf.dddexample.cms.service.dto.ArticleView;
import com.hxf.dddexample.cms.service.dto.ArticleViewAssembler;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleQueryService {

    @Resource
    private ArticleEntityRepository entityRepository;

    @Resource
    private ArticleViewAssembler viewAssembler;

    @Resource
    private ArticleJdbcRepository jdbcRepository;

    /**
     * 直接通过数据模型查询并返回
     *
     * @param query
     * @return
     */
    public PageBean pageList(ArticlePageQuery query) {

        Long count = jdbcRepository.countForQueryList(query.getKeyword());
        if (count == 0) {
            return new PageBean();
        }

        List<CmsArticle> cmsArticles = jdbcRepository.queryList(query.getKeyword(), (query.getPage() - 1) * query.getPageSize(), query.getPageSize());
        List<ArticleView> viewList = this.toViewList(cmsArticles);
        PageBean<ArticleView> pageBean = new PageBean<>();
        pageBean.setTotal(count.intValue());
        pageBean.setList(viewList);
        pageBean.setPage(query.getPage());
        pageBean.setPageSize(query.getPageSize());
        return pageBean;
    }

    private List<ArticleView> toViewList(List<CmsArticle> cmsArticles) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<ArticleView> viewList = cmsArticles.stream().map(a -> {
            ArticleView view = new ArticleView();
            view.setArticleId(a.getArticleId());
            view.setTitle(a.getTitle());
            view.setContent(a.getContent());
            view.setPublishState(PublishState.getByCode(a.getPublishState()).getMsg());
            view.setCreatedTime(format.format(a.getCreatedTime()));
            view.setModifiedTime(format.format(a.getModifiedTime()));
            return view;
        }).collect(Collectors.toList());

        return viewList;
    }

    public ArticleView articleDetail(String articleId) {
        ArticleEntity entity = entityRepository.load(new ArticleId(articleId));
        return viewAssembler.assembler(entity);
    }
}
