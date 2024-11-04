package com.group9.eventgaze.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_session")
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "session_token")
    private String sessionToken;

    @Column(name = "userRole")
    private String userRole;

    @Column(name = "session_start_time")
    private LocalDateTime sessionStartTime;

    @Column(name = "session_end_time")
    private LocalDateTime SessionEndTIme;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private Users users;



}
