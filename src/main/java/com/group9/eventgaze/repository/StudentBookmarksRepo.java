package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.StudentBookmarks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentBookmarksRepo extends JpaRepository<StudentBookmarks,Long> {
}
