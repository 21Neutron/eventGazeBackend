package com.group9.eventgaze.service;


import com.group9.eventgaze.entity.*;
import com.group9.eventgaze.entity.dto.EventRequestDTO;
import com.group9.eventgaze.repository.CollegesRepo;
import com.group9.eventgaze.repository.EventsRepo;
import com.group9.eventgaze.repository.PublishersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;



@Service
public class EventsService {

    @Autowired
    private EventsRepo eventsRepo;

    @Autowired
    private EventCategoryService eventCategoryService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private CollegesRepo collegesRepo;

    @Autowired
    private CollegesService collegesService;

    @Autowired
    private PublishersRepo publishersRepo;

    @Autowired
    private PublishersService publishersService;

    @Autowired
    private UsersService usersService;


    public List<Events> getAllEvents() {
        return eventsRepo.findAll();
    }

    // for creating an event

    public void createEvent(EventRequestDTO eventRequestDTO) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate eventDate = LocalDate.parse(eventRequestDTO.getEventDate(), formatter);

        EventCategory eventCategory = eventCategoryService.findByIdOrThrow(eventRequestDTO.getEventCategoryId());

        Users user = usersService.findByIdOrThrow(eventRequestDTO.getUserId());
        Publishers publishers = publishersService.findByUser(user);

        Events event = new Events();
        event.setEventName(eventRequestDTO.getEventName());
        event.setEventDescription(eventRequestDTO.getEventDescription());
        event.setEventDate(eventDate);
        event.setEventTags(eventRequestDTO.getEventTags());
        event.setEventCategory(eventCategory);
        event.setPublishers(publishers);

        Colleges college = collegesService.findById(eventRequestDTO.getColleges()); // Find the college by its ID
        event.setCollege(college);

        Events savedEvent = eventsRepo.save(event);

        if (eventRequestDTO.getEventArt() != null && !eventRequestDTO.getEventArt().isEmpty()) {
            String fileUrl = s3Service.uploadFile("events/" + eventRequestDTO.getEventArt().getOriginalFilename(), eventRequestDTO.getEventArt());
            savedEvent.setEventArt(fileUrl);

            eventsRepo.save(savedEvent);
        }
    }

    // For finding events by event ID

    public Optional<Events> findEventById(Long myId){
        return eventsRepo.findById(myId);
    }

    //For fetching event by the event category ID


    public List<Events> getEventsByCategory(Long categoryId) {
        return eventsRepo.findByEventCategory_CategoryId(categoryId);
    }

//    For fetching events by college ID

    public List<Events> getEventsByCollegeId(Long collegeId){
        return eventsRepo.findByCollege_CollegeId(collegeId);
    }

//    For deleting an event with the eventId

    public void deleteEventById(Long myId){
        eventsRepo.deleteById(myId);
    }

    public Events updateEventById(Long myId, Events newEvent) {

        Events oldEvent = eventsRepo.findById(myId).orElse(null);

        if (oldEvent != null && newEvent != null) {

            oldEvent.setEventName(newEvent.getEventName() != null && !newEvent.getEventName().isEmpty() ? newEvent.getEventName() : oldEvent.getEventName());

            oldEvent.setEventDescription(newEvent.getEventDescription() != null && !newEvent.getEventDescription().isEmpty() ? newEvent.getEventDescription() : oldEvent.getEventDescription());


            oldEvent.setEventTags(newEvent.getEventTags() != null && !newEvent.getEventTags().isEmpty() ? newEvent.getEventTags() : oldEvent.getEventTags());


            eventsRepo.save(oldEvent);
        }
        return oldEvent;
    }


}
