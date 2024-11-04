package com.group9.eventgaze.entity;



import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;



@Data
@Entity
@Table(name = "events")
public class Events {

    @Id
    @Column(name = "event_id",nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_description")
    private String eventDescription;


    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "event_scope")
    private String eventScope;

    @JoinColumn(name = "publisher_id",referencedColumnName = "publisher_id")
    @ManyToOne
    private Publishers publishers;

    @Column(name = "event_tags")
    private String eventTags;

    @Column(name = "event_art")
    private String eventArt;
}
