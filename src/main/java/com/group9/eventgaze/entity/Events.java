package com.group9.eventgaze.entity;



import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "events")
public class Events {

    @Id
    @Field("event_id")
    private ObjectId eventId;

    @Field("event_name")
    private String eventName;

    @Field("event_description")
    private String eventDescription;

    @Field("event_scope")
    private String eventScope;

    @Field("publisher_id")
    private Integer publisherId;

    @Field("event_tags")
    private String eventTags;
}
