package com.myblog.controller;

import com.myblog.domain.ResponseResult;
import com.myblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    /**
     * 获得所有友情链接
     * @return
     */
    @GetMapping("/getAllLink")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }
}
