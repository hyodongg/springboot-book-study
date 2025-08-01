package com.example.demo.service;

import com.example.demo.dto.ArticleRequest;
import com.example.demo.dto.ArticleResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Article;
import com.example.demo.model.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    private ArticleResponse mapToArticleResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .created(article.getCreated())
                .updated(article.getUpdated())
                .memberId(article.getMember().getId())
                .name(article.getMember().getName())
                .email(article.getMember().getEmail()).build();
    }

    public ArticleResponse create(Long memberId,ArticleRequest articleRequest) {
         Member member = memberRepository.findById(memberId)
                 .orElseThrow(NotFoundException::new);
         Article article = Article.builder()
                 .title(articleRequest.getTitle())
                 .description((articleRequest.getDescription()))
                 .member(member)
                 .build();
         articleRepository.save(article);
         return mapToArticleResponse(article);
    }

    public List<ArticleResponse> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(this::mapToArticleResponse)
                .toList();
    }

    public List<ArticleResponse> findByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundException::new);
        return articleRepository.findByMember(member)
                .stream()
                .map(this::mapToArticleResponse)
                .toList();
    }

    public ArticleResponse findById(Long id){
        Article article = articleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return mapToArticleResponse(article);
    }

    public ArticleResponse update(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        articleRepository.save(article);
        return mapToArticleResponse(article);
    }

    public void delete(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        articleRepository.delete(article);
    }
}
