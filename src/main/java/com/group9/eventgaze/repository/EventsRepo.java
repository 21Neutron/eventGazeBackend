package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface EventsRepo extends JpaRepository<Events, Long> {

}
