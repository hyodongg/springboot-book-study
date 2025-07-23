package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",length=256,nullable=false,unique=true)
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="age",nullable=false)
    private Integer age;
    // DB와 연동하지 않고 애플리케이션 내부에서만 사용할 때
    //@Transient
    //private String address;
    @ToString.Exclude
    @OneToMany(mappedBy = "member",fetch = FetchType.EAGER)
    private List<Article> articles;
}
