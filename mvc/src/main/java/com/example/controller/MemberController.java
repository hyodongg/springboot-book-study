package com.example.controller;

import com.example.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MemberController {
    private List<Member> members = List.of(
            new Member(1L,"윤서준","Seojun@naver.com",10),
            new Member(2L,"윤광철","Kwangcheol",43),
            new Member(3L,"공미영","Miyeong",43),
            new Member(4L,"김도운","Doyun",43)
    );

    @GetMapping("/member/list")
    public String getMembers(Model model){
        model.addAttribute("members",members);
        return "member-list";
    }
}
