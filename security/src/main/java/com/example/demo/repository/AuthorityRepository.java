package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Authority;
import com.example.demo.model.Member;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    List<Authority> findByMember(Member member);
}
