package com.group9.eventgaze.service;

import com.group9.eventgaze.entity.Events;
import com.group9.eventgaze.repository.EventsRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class EventsService {

    @Autowired
    private EventsRepo eventsRepo;



    public void saveEvent(Events events)  {
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

    public Events updateEventById(ObjectId myId, Events newEvent) {
        Events oldEvent = eventsRepo.findById(myId).orElse(null);

        if (oldEvent != null && newEvent != null) {
            oldEvent.setEventName(newEvent.getEventName() != null && !newEvent.getEventName().isEmpty() ? newEvent.getEventName() : oldEvent.getEventName());
            oldEvent.setEventDescription(newEvent.getEventDescription() != null && !newEvent.getEventDescription().isEmpty() ? newEvent.getEventDescription() : oldEvent.getEventDescription());
            oldEvent.setEventScope(newEvent.getEventScope() != null && !newEvent.getEventScope().isEmpty() ? newEvent.getEventScope() : oldEvent.getEventScope());
            oldEvent.setEventTags(newEvent.getEventTags() != null && !newEvent.getEventTags().isEmpty() ? newEvent.getEventTags() : oldEvent.getEventTags());

            eventsRepo.save(oldEvent);
        }
        return oldEvent;
    }


}
