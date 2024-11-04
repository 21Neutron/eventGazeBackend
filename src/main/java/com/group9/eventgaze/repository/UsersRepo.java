package com.group9.eventgaze.repository;


import com.group9.eventgaze.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
}
