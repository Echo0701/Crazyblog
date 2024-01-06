package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.domain.Article;
import com.myblog.domain.ResponseResult;

public interface ArticleService extends IService<Article> {
    /**
     * 热门文章列表
     * @return
     */
    ResponseResult hotArticleList();

    /**
     * 分类查询文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    /**
     * 根据id查询文章详情
     * @param id
     * @return
     */
    ResponseResult getArticleDetail(Long id);
}
