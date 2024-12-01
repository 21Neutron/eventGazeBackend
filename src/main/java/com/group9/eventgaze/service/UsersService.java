package com.group9.eventgaze.service;

import com.group9.eventgaze.entity.Users;
import com.group9.eventgaze.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepo usersRepo;

    public Users findByIdOrThrow(Long userId) {
        return usersRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

}
