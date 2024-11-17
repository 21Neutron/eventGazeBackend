package com.group9.eventgaze.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String sessionToken;
    private String userRole;
    private Long userId;
}
