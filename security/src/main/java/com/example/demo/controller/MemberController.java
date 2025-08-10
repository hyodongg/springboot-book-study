package com.example.demo.controller;

import com.example.demo.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class MemberController {
    private List<Member> members = List.of(
            new Member(1L, "조효동", "whgyehdjhd@naver.com", null),
            new Member(2L, "조효창", "gyckdjhc@naver.com", null),
            new Member(3L, "공미영", "MiyeongKong@hanbit.co.kr", null),
            new Member(4L, "김도윤", "DoyunKim@hanbit.co.kr", null)
    );

    @GetMapping("/member/list")
    public String getMembers(Model model) {
        model.addAttribute("members", members);
        return "/member-list";
    }
}