package com.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.Mapper.ArticleMapper;
import com.myblog.domain.Article;
import com.myblog.domain.ResponseResult;
import com.myblog.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//ServiceImpl:mybatisPlus 官方提供的
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成 ResponseResult 返回，吧所有查询条件写在 queryWrapper里面
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //查询的不能是草稿，也就是Status字段不能是0
        queryWrapper.eq(Article::getStatus,0);
        //按照浏览量进行排序，也就是根据ViewCount字段降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查询10条消息：当前显示第一页的数据，每页显示10条数据
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);
        //获取最终的查询结果
        List<Article> articles = page.getRecords();

        return ResponseResult.okResult(articles);
    }
}
