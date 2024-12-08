package com.group9.eventgaze.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.List;


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

    @Lob
    @Column(name = "event_description",columnDefinition = "Text")
    private String eventDescription;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "event_venue")
    private String eventVenue;

    @Column(name = "event_tags")
    private String eventTags;

    @Column(name = "event_art")
    private String eventArt;


    @JoinColumn(name = "publisher_id",referencedColumnName = "publisher_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Publishers publishers;


    @ManyToOne
    @JoinColumn(name = "event_category_id",referencedColumnName = "event_category_id")
    private EventCategory eventCategory;


    @ManyToOne
    @JoinColumn(name = "college_id",referencedColumnName = "college_id")
    private Colleges college;
}
