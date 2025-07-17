package com.example.demo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {

    List<Member> findByName(String name);
    List<Member> findByNameAndEmail(String name, String email);
    List<Member> findByAgeGreaterThan(int age);
    @Query("select * from member where age >= 13 and age <= 19")
    List<Member> findTeenAge();

}
