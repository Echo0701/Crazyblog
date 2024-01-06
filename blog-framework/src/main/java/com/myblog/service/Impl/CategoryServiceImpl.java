package com.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.Mapper.CategoryMapper;
import com.myblog.constants.SystemConstants;
import com.myblog.domain.Article;
import com.myblog.domain.Category;
import com.myblog.domain.ResponseResult;
import com.myblog.service.ArticleService;
import com.myblog.service.CategoryService;
import com.myblog.utils.BeanCopyUtils;
import com.myblog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        //要求getstatus == 1
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //查询文章列表，条件就是上一行的articleWrapper
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id，并且去重
        //使用stream流来处理上一行得到的文章表集合
        Set<Long> categoryIds = articleList.stream()
                .map(new Function<Article, Long>() {
                    @Override
                    public Long apply(Article article) {
                        return article.getCategoryId();
                    }
                })
                //把文章的分类id转换成set集合
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        //封装成CategoryVo 实体类后返回给前端
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}
