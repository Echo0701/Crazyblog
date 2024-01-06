package com.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.Mapper.ArticleMapper;
import com.myblog.constants.SystemConstants;
import com.myblog.domain.Article;
import com.myblog.domain.ResponseResult;
import com.myblog.service.ArticleService;
import com.myblog.utils.BeanCopyUtils;
import com.myblog.vo.HotArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
//ServiceImpl:mybatisPlus 官方提供的
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成 ResponseResult 返回，吧所有查询条件写在 queryWrapper里面
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //查询的不能是草稿，也就是Status字段不能是0
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序，也就是根据ViewCount字段降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查询10条消息：当前显示第一页的数据，每页显示10条数据
        Page<Article> page = new Page<>(SystemConstants.ARTICLE_STATUS_CURRENT,SystemConstants.ARTICLE_STATUS_SIZE);
        page(page,queryWrapper);
        //获取最终的查询结果
        List<Article> articles = page.getRecords();

//        //统一响应格式的优化（用VO封装返回的结果对象）
//        List<HotArticleVO> articleVos = new ArrayList<>();
//        for (Article xxarticle : articles) {
//            HotArticleVO xxvo = new HotArticleVO();
//            //使用spring提供的BeanUtils类，来实现bean拷贝。第一个参数是源数据，第二个参数是目标数据，把源数据拷贝给目标数据
//            //虽然xxarticle里面有很多不同的字段，但是xxvo里面只有3个字段(没有具体数据)，所以拷贝之后，就能把xxvo里面的三个字段填充具体数据
//            BeanUtils.copyProperties(xxarticle,xxvo); //xxarticle就是Article实体类，xxvo就是HotArticleVo实体类
//            //把我们要的数据(也就是上一行的xxvo)封装成集合
//            articleVos.add(xxvo);
//        }

        List<HotArticleVO> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVO.class);

        return ResponseResult.okResult(articleVos);
    }
}
