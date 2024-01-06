package com.myblog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.domain.Link;
import com.myblog.domain.ResponseResult;

public interface LinkService extends IService<Link>{
    /**
     * 查询所有链接
     * @return
     */
    ResponseResult getAllLink();
}
