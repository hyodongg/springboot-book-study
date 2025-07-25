package com.example.demo.controller;

import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse post(@RequestBody MemberRequest memberRequest){
        return memberService.create(memberRequest);
    }

    @GetMapping
    public List<MemberResponse> getAll(){
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public MemberResponse get(@PathVariable("id") Long id){
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public MemberResponse update(@PathVariable("id") Long id,@RequestBody MemberRequest memberRequest){
        return memberService.update(id,memberRequest);
    }

    @PatchMapping("/{id}")
    public MemberResponse patch(@PathVariable("id") Long id,@RequestBody MemberRequest memberRequest){
        return memberService.patch(id,memberRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        memberService.deleteById(id);
    }
}
