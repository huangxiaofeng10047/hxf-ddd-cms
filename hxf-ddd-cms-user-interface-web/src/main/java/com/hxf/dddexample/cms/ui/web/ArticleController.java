package com.hxf.dddexample.cms.ui.web;



import com.feiniaojin.gracefulresponse.data.PageBean;
import com.hxf.dddexample.cms.service.applicationservice.ArticleCommandService;
import com.hxf.dddexample.cms.service.applicationservice.ArticleQueryService;
import com.hxf.dddexample.cms.service.dto.ArticleCreateCmd;
import com.hxf.dddexample.cms.service.dto.ArticleModifyContentCmd;
import com.hxf.dddexample.cms.service.dto.ArticleModifyTitleCmd;
import com.hxf.dddexample.cms.service.dto.ArticlePageQuery;
import com.hxf.dddexample.cms.service.dto.ArticlePublishCmd;
import com.hxf.dddexample.cms.service.dto.ArticleView;
import jakarta.annotation.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleQueryService queryService;

    @Resource
    private ArticleCommandService commandService;

    @RequestMapping("/get")
    public ArticleView get(@Param("articleId") String articleId) {
        return queryService.articleDetail(articleId);
    }

    @RequestMapping("/createArticleDraft")
    public void createArticleDraft(@RequestBody ArticleCreateCmd cmd) {
        commandService.newDraft(cmd);
    }

    @RequestMapping("/modifyTitle")
    public void modifyTitle(@RequestBody ArticleModifyTitleCmd cmd) {
        commandService.modifyTitle(cmd);
    }

    @RequestMapping("/publishArticle")
    public void publishArticle(@RequestBody ArticlePublishCmd cmd) {
        commandService.publishArticle(cmd);
    }
    @RequestMapping("/modifyContent")
    public void modifyTitle(@RequestBody ArticleModifyContentCmd cmd) {
        commandService.modifyContent(cmd);
    }

    @RequestMapping("/pageList")
    public PageBean<ArticleView> pageList( ArticlePageQuery pageQuery) {
        return queryService.pageList(pageQuery);
    }

}
