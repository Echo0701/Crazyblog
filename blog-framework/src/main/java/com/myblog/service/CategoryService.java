package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.domain.Category;
import com.myblog.domain.ResponseResult;

public interface CategoryService extends IService<Category> {
    //查询文章分类的接口
    ResponseResult getCategoryList();
}
