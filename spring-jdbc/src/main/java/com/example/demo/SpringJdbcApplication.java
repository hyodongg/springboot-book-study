package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SpringJdbcApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        var members = memberRepository.findAll();
        log.info("{}",members);
        var member = memberRepository.findById(1L);
        log.info("{}",member);
    }
}
