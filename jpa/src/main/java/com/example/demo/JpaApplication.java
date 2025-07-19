package com.example.demo;

import com.example.demo.model.Article;
import com.example.demo.model.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JpaApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        var member = Member.builder().name("조효창")
                .email("gyckdjhc@naver.com")
                .age(28)
                .build();
        memberRepository.save(member);

        // 동적 쿼리용 example 객체
        //Example<Member> example = Example.of(Member.builder().name("조효동").age(25).build(),
        //        ExampleMatcher.matchingAll());
        //List<Member> members=memberRepository.findAll(example);
        //log.info("검색 결과 : {} ",members);

        var article = Article.builder()
                .title("효동's 스프링 부트")
                .description("스프링 부트 예제입니다.")
                .member(member)
                .build();
        articleRepository.save(article);

        var articles = articleRepository.findAll();

        for(Article article1 : articles){
            log.info("{}", article1);
        }
    }

}
