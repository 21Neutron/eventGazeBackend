package com.group9.eventgaze.controller;

import com.group9.eventgaze.entity.Events;
import com.group9.eventgaze.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;


//    Returns all events in a list
    @GetMapping("/getAll")
    public List<Events> getAll(){
        return eventsService.getAllEvents();
    }



//    For creating a new event


    @PostMapping("/create")
    public ResponseEntity<String> createEvent(
            @RequestParam("eventName") String eventName,
            @RequestParam("eventDescription") String eventDescription,
            @RequestParam("eventDate") String eventDateStr,
            @RequestParam("eventScope") String eventScope,
            @RequestParam("eventTags") String eventTags,
            @RequestParam("eventArt") MultipartFile file
    ) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate eventDate = LocalDate.parse(eventDateStr, formatter);


            Events event = new Events();
            event.setEventName(eventName);
            event.setEventDescription(eventDescription);
            event.setEventDate(eventDate);
            event.setEventScope(eventScope);
            event.setEventTags(eventTags);


            eventsService.saveEvent(event, file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Event created successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating event.");
        }
    }




//  For getting event by ID


    @GetMapping("id/{myId}")
    public ResponseEntity<Events> getEventById(@PathVariable Long myId){
        Optional<Events> events = eventsService.findEventById(myId);

        if(events.isPresent()){
            return ResponseEntity.ok(events.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



//  For deleting event by ID


    @DeleteMapping("id/{myId}")
    public ResponseEntity<Void>deleteEventById(@PathVariable Long myId) {
        Optional<Events> events = eventsService.findEventById(myId);
        if(events.isPresent()){
            eventsService.deleteEventById(myId);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


//  For updating or editing an event


    @PutMapping("id/{myId}")
    public Events editByEventId(@PathVariable Long myId, @RequestBody Events newEvent){
        return eventsService.updateEventById(myId, newEvent);
    }


}

