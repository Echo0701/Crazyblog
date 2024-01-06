package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.domain.Article;
import com.myblog.domain.ResponseResult;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
