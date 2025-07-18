package com.example.demo.mapper;

import com.example.demo.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

// 어노테이션기반 SQL 매핑
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

    @Insert("""
insert into article(title,description,created,updated,member_id) values(#{article.title}
, #{article.description}, current_timestamp, current_timestamp,#{article.memberId}
""")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("article") Article article);

    @Update("""
update article set title=#{title}, description=#{description},updated=current_timestamp where id = #{id}
""")
    int update(@Param("title") String title, @Param("description") String description, @Param("id") Long id);

    @Delete("delete article where id=#{id}")
    int deleteById(@Param("id") Long id);

    @Delete("delete article where member_id=memberId")
    int deleteByMemberId(@Param("memberId") long MemberId);

    @Delete("delete article")
    int deleteAll();
}
