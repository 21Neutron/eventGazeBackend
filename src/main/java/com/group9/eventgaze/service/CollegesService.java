package com.group9.eventgaze.service;

import com.group9.eventgaze.entity.Colleges;
import com.group9.eventgaze.repository.CollegesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollegesService {

    @Autowired
    private CollegesRepo collegesRepo;

    public List<Colleges> getCollegeList(){
        return collegesRepo.findAll();
    }

    public Optional<Colleges> getCOllegeByID(Long myId){
        return collegesRepo.findById(myId);
    }
}
