package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepo extends JpaRepository<UserSession,Long> {
}
