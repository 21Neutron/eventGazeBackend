package com.group9.eventgaze.controller;

import com.group9.eventgaze.entity.dto.EventRequestDTO;
import com.group9.eventgaze.entity.Events;
import com.group9.eventgaze.service.EventCategoryService;
import com.group9.eventgaze.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("eventgaze/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @Autowired
    private EventCategoryService eventCategoryService;

//    Returns all events in a list
    @GetMapping("/getAll")
    public ResponseEntity<List<Events>> getAllEvents(){
        List<Events> events = eventsService.getAllEvents();

        if (events.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(events,HttpStatus.OK);
        }
    }



//    For creating a new event


    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@ModelAttribute  EventRequestDTO requestDTO) {
        try {
            eventsService.createEvent(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

//    Fetch event with categoryId


//    @GetMapping("/category/{categoryId}")
//    public ResponseEntity<List<Events>> getEventsByCategory(@PathVariable Long categoryId) {
//        List<Events> events = eventsService.getEventsByCategory(categoryId);
//        if (events.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(events);
//    }



//    Fetch events with college id
//
//    public ResponseEntity<List<Events>>getEventsByCollegeId(@PathVariable Long collegId){
//        List<Events> events = eventsService.getEventsByCollegeId(collegId);
//        if(events.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(events);
//    }




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
    public ResponseEntity<Events> editByEventId(@PathVariable Long myId, @RequestBody Events newEvent) {
        Events updatedEvent = eventsService.updateEventById(myId, newEvent);

        if (updatedEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        }
    }


}

