package com.group9.eventgaze.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "student_bookmarks")
public class StudentBookmarks {

    @Id
    @Column(name = "bookmark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookMarkId;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Students studentEntity;

    @ManyToOne
    @JoinColumn(name = "event_id",referencedColumnName = "event_id")
    private Events events;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
