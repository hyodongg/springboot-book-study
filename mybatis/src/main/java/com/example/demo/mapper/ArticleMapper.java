package com.example.demo.mapper;

import com.example.demo.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {
    @Results(id="articleResult", value={
            @Result(column = "title",property="title"),
            @Result(column = "content", property="description"),
            @Result(column = "member_id",property="memberId")
    })
    @Select("select * from article")
    List<Article> selectAll();

    @Select("select count(*) from article")
    int selectAllCount();

    @ResultMap("articleResult")
    @Select("select * from article where id=#{id}")
    Optional<Article> selectById(@Param("id") Long id);

    @Select("select * from article where member_id=#{memberId}")
    @ResultMap("articleResult")
    List<Article> selectByMemberId(@Param("memberId") Long memberId);


}
