package com.group9.eventgaze.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class Users{


    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @Column(name = "user_mail")
    @Email
    private String userEmail;


    @Column(name = "user_password")
    private String userPassword;


    @Column(name = "user_role")
    private String userRole;


    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
