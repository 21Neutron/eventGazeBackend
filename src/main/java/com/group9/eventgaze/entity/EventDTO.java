package com.group9.eventgaze.entity;

import lombok.Data;

@Data
public class EventDTO {
    private String eventName;
    private String eventDescription;
    private String eventDate;  // Keep this as a String to accept date input from Postman
    private String eventScope;
    private String eventTags;
    private long publisherId;
}

