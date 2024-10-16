package com.hxf.dddexample.cms.infrastructure.persistence;


import java.util.List;

import com.hxf.dddexample.cms.infrastructure.persistence.data.CmsArticle;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 基于Spring Data Jdbc实现的数据表Repository
 */
@Repository
public interface ArticleJdbcRepository extends CrudRepository<CmsArticle, Long> {

    @Query("select * from cms_article limit :offset,:pageSize")
    List<CmsArticle> queryList(@Param("title") String title, @Param("offset") Integer offset,
        @Param("pageSize") Integer pageSize);

    @Query("select count(*) from cms_article")
    Long countForQueryList(@Param("title") String title);

    @Query("select * from cms_article where article_id = :articleId limit 1")
    CmsArticle queryOne(@Param("articleId") String articleId);
}
