package com.syr.service;

import com.syr.pojo.Article;
import com.syr.pojo.PageBean;

public interface ArticleService {
    //增加文章
    void add(Article article);
    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
