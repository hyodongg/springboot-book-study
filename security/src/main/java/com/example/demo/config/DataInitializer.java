package com.example.demo.config;

import com.example.demo.model.Authority;
import com.example.demo.model.Member;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (memberRepository.count() == 0) {
            Member admin = memberRepository.save(Member.builder()
                    .name("조효동")
                    .email("whgyehdjhd@naver.com")
                    .password(passwordEncoder.encode("password")).build());
            Member user = memberRepository.save(Member.builder()
                    .name("조효창")
                    .email("gyckdjhc@naver.com")
                    .password(passwordEncoder.encode("password")).build());
            authorityRepository.save(Authority.builder()
                    .authority("ROLE_USER").member(user).build());
            authorityRepository.save(Authority.builder()
                    .authority("ROLE_USER").member(admin).build());
            authorityRepository.save(Authority.builder()
                    .authority("ROLE_ADMIN").member(admin).build());
        }
    }
}
