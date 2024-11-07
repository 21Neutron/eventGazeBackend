package com.group9.eventgaze.service;


import com.group9.eventgaze.entity.Events;
import com.group9.eventgaze.repository.EventsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class EventsService {

    @Autowired
    private EventsRepo eventsRepo;

    @Autowired
    private S3Service s3Service;


    public List<Events> getAllEvents() {
        return eventsRepo.findAll();
    }

    // For saving events

    public void saveEvent(Events events, MultipartFile file) throws IOException {

        Events savedEvent = eventsRepo.save(events);

        if(file != null && !file.isEmpty()){
            String fileUrl = s3Service.uploadFile("events/"+file.getOriginalFilename(),file);

            savedEvent.setEventArt(fileUrl);
            eventsRepo.save(savedEvent);
        }
    }

    // For finding events by Id

    public Optional<Events> findEventById(Long myId){
        return eventsRepo.findById(myId);
    }

    public void deleteEventById(Long myId){
        eventsRepo.deleteById(myId);
    }

    public Events updateEventById(Long myId, Events newEvent) {

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
