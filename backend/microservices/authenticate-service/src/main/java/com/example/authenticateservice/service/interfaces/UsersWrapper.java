package com.example.authenticateservice.service.interfaces;

import com.example.authenticateservice.entity.UsersEntity;

public interface UsersWrapper {

    void saveEntity(UsersEntity usersEntity);

    String generateToken(String username);

    void validationCheck(String token);
}
