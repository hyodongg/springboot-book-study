package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "persistent_logins")
@Data
public class PersistentLogin {
    @Id
    @Column(length=64)
    private String series;
    @Column(length=64,nullable=false)
    private String username;
    @Column(length=64,nullable=false)
    private String token;
    @Column(name="last_userd",nullable = false)
    private LocalDateTime lastUsed;
}
