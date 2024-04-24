package org.syr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.syr.mapper.ArticleMapper;
import org.syr.pojo.Article;
import org.syr.service.ArticleService;
import org.syr.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        article.setCreateUser(userid);
        articleMapper.add(article);
    }
}
