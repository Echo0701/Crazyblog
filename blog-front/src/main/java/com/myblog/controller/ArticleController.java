package com.myblog.controller;

import com.myblog.domain.Article;
import com.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    /**
     * 注入公共模块的ArticleService接口
     */
    @Autowired
    private ArticleService articleService;

    /**
     * 查询数据库的所有数据
     * @return
     */
    @GetMapping("/list")
    public List<Article> test() {
        return articleService.list();
    }
}
