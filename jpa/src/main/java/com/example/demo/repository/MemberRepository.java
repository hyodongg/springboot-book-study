package com.example.demo.repository;

import com.example.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // JpaRepository를 상속해서
            // 안 써도 되지만 해당 인터페이스가 레포지토리라는 사실을 명시적으로 나타내기 위함
public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByName(String name);
    List<Member> findByNameAndEmail(String name, String email);
    // 이름의 시작으로 회원 조회
    List<Member> findByNameStartingWith(String name);
    List<Member> findByNameEndingWith(String name);
    // 이름을 포함하는 회원 조회
    List<Member> findByNameContaining(String name);
    // 이름을 포함하는 회원 조회
    // findByName("%효%") 처럼 쓰기
    List<Member> findByNameLike(String name);

    // JPQL 예시 테이블과 컬럼이름이 아닌 클래스와 프로퍼티 이름으로, 엔티티는 반드시 별칭 사용
    @Query("select m from Member m where m.name = :name")
    List<Member> findMember(@Param("name") String name);

    // JPQL을 사용하여 JOIN, GROUP BY, ORDER BY
//    @Query("""
//                SELECT m.name, m.email, COUNT(a.id) as count
//                FROM Member m LEFT JOIN Article a ON a.member = m
//                GROUP BY m ORDER BY count DESC
//""")
//    List<Object[]> getMemberStatsObject();
}
