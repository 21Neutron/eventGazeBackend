package com.group9.eventgaze.service;

import com.group9.eventgaze.entity.Events;
import com.group9.eventgaze.repository.EventsRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventsService {

    @Autowired
    private EventsRepo eventsRepo;



    public void saveEvent(Events events){
        eventsRepo.save(events);
    }


    public List<Events> getAllEvents() {
        return eventsRepo.findAll();
    }


    public Optional<Events> findById(ObjectId myId){
        return eventsRepo.findById(myId);
    }

    public void deleteEventById(ObjectId myId){
        eventsRepo.deleteById(myId);
    }


}
