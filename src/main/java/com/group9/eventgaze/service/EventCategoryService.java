package com.group9.eventgaze.service;

import com.group9.eventgaze.entity.EventCategory;
import com.group9.eventgaze.repository.EventCategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCategoryService {

    private EventCategoryRepo eventCategoryRepo;

    public List<EventCategory>getAllCategory(){
        return eventCategoryRepo.findAll();
    }

}
