package com.group9.eventgaze.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EventRequestDTO {
    private String eventName;
    private String eventDescription;
    private String eventDate;
    private String eventScope;
    private String eventTags;
    private Long eventCategoryId;
    private MultipartFile eventArt;
}
