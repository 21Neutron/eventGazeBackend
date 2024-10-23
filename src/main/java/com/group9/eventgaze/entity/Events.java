package com.group9.eventgaze.entity;



import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "events")
public class Events {

    @Id
    private ObjectId event_id;

    private String event_name;

    private String event_description;

    private String event_scope;

    private int publisher_id;

    private String event_tags;


}
