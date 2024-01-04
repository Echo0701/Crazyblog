package com.myblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.Mapper.ArticleMapper;
import com.myblog.domain.Article;
import com.myblog.service.ArticleService;
import org.springframework.stereotype.Service;


@Service
//ServiceImpl:mybatisPlus 官方提供的
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
