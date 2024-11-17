package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.Colleges;
import com.group9.eventgaze.entity.dto.CollegeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegesRepo extends JpaRepository<Colleges,Long>{

//    List<Colleges> findAllByCollegeId(List<CollegeDTO> colleges);
}
