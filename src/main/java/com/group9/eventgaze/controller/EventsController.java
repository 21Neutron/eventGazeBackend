package com.group9.eventgaze.controller;

import com.group9.eventgaze.entity.Events;
import com.group9.eventgaze.service.EventsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping
    public List<Events> getAll(){
        return eventsService.getAllEvents();
    }

    @PostMapping()
    public Events createEvent(@RequestBody Events myevents){
        eventsService.saveEvent(myevents);
        return myevents;
    }

    @GetMapping("id/{myId}")
    public Events getEventById(@PathVariable ObjectId myId){
        return eventsService.findById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public String deleteEventbyId(@PathVariable ObjectId myId) {
        eventsService.deleteEventById(myId);
        return "successfully deleted";
    }

    @PutMapping("id/{myId}")
    public Events updateByEventId(@PathVariable ObjectId myId, @RequestBody Events newEvent){
        return eventsService.updateEventById(myId, newEvent);
    }
}

