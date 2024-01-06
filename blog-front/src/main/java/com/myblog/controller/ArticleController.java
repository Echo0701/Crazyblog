package com.myblog.controller;

import com.myblog.domain.Article;
import com.myblog.domain.ResponseResult;
import com.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // ----------------------测试 mybatisPlus ----------------
    /**
     * 查询数据库的所有数据
     * @return
     */
    @GetMapping("/list")
    public List<Article> test() {
        return articleService.list();
    }

    // --------------------- 测试 统一响应格式 -------------------

    /**
     * 查询热门文章
     * @return
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成ResponseResult 返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

//-----------------------------------分页查询文章列表-------------------------------------
    /**
     * 分页查询文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    // --------------------- 查询文章详情 -----------------------------------------

    /**
     * 根据 id 查询文章详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }


}
