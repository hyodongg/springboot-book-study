package com.example.demo.controller;


import com.example.demo.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

@Controller
public class ExpressionController {
    private final List<Member> members = List.of(
            new Member("조효동", "whgyehdjhd@naver.com", 25),
            new Member("조효창", "gyckd@naver.com", 28)
    );

    // 자바 객체 사용하기
    @GetMapping("/object")
    public String getMember(Model model) {
        model.addAttribute("member", members.get(0));
        return "expression/object";
    }

    // 유틸리티 객체 사용하기
    // 컨트롤러가 전달한 날짜나 숫자를 원하는 형식으로 출력하기위해(calendars,numbers 제공)
    @GetMapping("/calendars")
    public String getCalendars(Model model) {
        Date date = Calendar.getInstance().getTime();
        model.addAttribute("date", date);
        return "expression/calendars";
    }

    @GetMapping("/numbers")
    public String getNumbers(Model model) {
        model.addAttribute("productPrice", 345620.2222);
        model.addAttribute("productCount", 10231412);
        return "expression/numbers";
    }

    // 조건문 사용하기
    @GetMapping("/condition")
    public String getCondition(Model model) {
        model.addAttribute("showWelcome",true);
        model.addAttribute("showDescription",false);
        return "expression/condition";
    }

    // 반복문 사용하기
    @GetMapping("/loop")
    public String getMemberList(Model model) {
        model.addAttribute("members",members);
        return "expression/loop";
    }
    // 링크 사용하기
    @GetMapping("/link")
    public String getLink(Model model) {
        model.addAttribute("id",1);
        return "expression/link";
    }
    @GetMapping("/member")
    public String getMemberByIdParam(@RequestParam(name="id",required = false) Integer id
            ,Model model) {
        if (id != null){
            model.addAttribute("member",members.get(id));
        }
        else{
            model.addAttribute("member",members.get(0));
        }
        return "expression/member";
    }
    @GetMapping("/member/{id}")
    public String getMemberByIdPath(@PathVariable("id") Integer id, Model model){
        model.addAttribute("member",members.get(id));
        return "expression/member";
    }
}
