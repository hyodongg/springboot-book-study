package com.example.demo;

import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyBatisApplication implements ApplicationRunner{
    private final MemberMapper memberMapper;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        var members = memberMapper.selectAll();
        log.info("회원 목록 = {}",members);
        log.info("======");
        var member = memberMapper.selectById(2L);
        log.info("2번 회원 =  {}",member);
    }
}
