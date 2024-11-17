package com.group9.eventgaze.entity;



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

    @Column(name = "event_scope")
    private String eventScope;


    @Column(name = "event_tags")
    private String eventTags;

    @Column(name = "event_art")
    private String eventArt;

    @ManyToMany
    @JoinTable(
            name = "event_colleges",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "college_id")
    )
    private List<Colleges> college;

    @JoinColumn(name = "publisher_id",referencedColumnName = "publisher_id")
    @ManyToOne
    private Publishers publishers;

    @JoinColumn(name = "event_category_id",referencedColumnName = "event_category_id")
    @ManyToOne
    private EventCategory eventCategory;

}
