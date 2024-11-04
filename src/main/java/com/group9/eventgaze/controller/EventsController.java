package com.group9.eventgaze.controller;

import com.group9.eventgaze.entity.Events;
import com.group9.eventgaze.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;


//    Returns all events in a list
    @GetMapping
    public List<Events> getAll(){
        return eventsService.getAllEvents();
    }



//    For creating a new event


    @PostMapping("/create")
    public String createEvent(
            @RequestParam("eventName") String eventName,
            @RequestParam("eventDescription") String eventDescription,
            @RequestParam("eventDate") String eventDateStr,
            @RequestParam("eventScope") String eventScope,
            @RequestParam("eventTags") String eventTags,
            @RequestParam("file") MultipartFile file
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
            return "Event created successfully";
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use 'dd-MM-yyyy'";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error while creating event.";
        }
    }




//  For getting event by ID


    @GetMapping("id/{myId}")
    public Events getEventById(@PathVariable Long myId){
        return eventsService.findEventById(myId).orElse(null);
    }



//  For deleting event by ID


    @DeleteMapping("id/{myId}")
    public String deleteEventById(@PathVariable Long myId) {
        eventsService.deleteEventById(myId);
        return "successfully deleted";
    }


//  For updating or editing an event


    @PutMapping("id/{myId}")
    public Events editByEventId(@PathVariable Long myId, @RequestBody Events newEvent){
        return eventsService.updateEventById(myId, newEvent);
    }


}

