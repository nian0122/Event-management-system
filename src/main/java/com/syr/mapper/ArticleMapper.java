package com.syr.mapper;

import org.apache.ibatis.annotations.*;
import com.syr.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
    List<Article> list(Integer userId, Integer categoryId, String state);
    @Select("select * from article where id = #{id}")
    Article findId(Integer id);

    @Update("update article set content = #{content}, update_time = #{updateTime} " +
            "where id = #{id}")
    void update(Article article);
    @Delete("delete from article where id = #{id}")
    void deleteById(Integer id);
}
