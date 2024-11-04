package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepo extends JpaRepository<UserSession,Long> {
}
