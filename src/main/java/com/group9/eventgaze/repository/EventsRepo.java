package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventsRepo extends JpaRepository<Events, Long> {

    List<Events> findByEventCategory_CategoryId(Long categoryId);

    List<Events> findByCollege_CollegeId(Long collegeId);


}
