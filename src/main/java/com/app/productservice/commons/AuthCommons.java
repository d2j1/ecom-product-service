package com.app.productservice.commons;

import com.app.productservice.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {

    private RestTemplate restTemplate;
    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token){
    // call the userService to validate the token
        String url = "http://localhost:8080/users/validate/"+token;

        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(url, UserDto.class);

        if( responseEntity.getBody() == null){
            // or throw some exception
            return null;
        }

        return responseEntity.getBody();
    }
}
