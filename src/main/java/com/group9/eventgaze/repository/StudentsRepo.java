package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepo extends JpaRepository<Students,Long> {
}
