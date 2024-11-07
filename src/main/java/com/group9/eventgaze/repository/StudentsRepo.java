package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepo extends JpaRepository<Students,Long> {
}
