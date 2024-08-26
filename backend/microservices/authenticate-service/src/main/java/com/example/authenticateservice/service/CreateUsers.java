package com.example.authenticateservice.service;

import com.example.authenticateservice.entity.UsersEntity;
import com.example.authenticateservice.repository.UserRepository;
import com.example.authenticateservice.service.interfaces.UsersWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUsers implements UsersWrapper {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTService jwtService;

    @Override
    public void saveEntity(UsersEntity usersEntity) {
        usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        userRepository.save(usersEntity);
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public void validationCheck(String token) {
        jwtService.validationToken(token);
    }
}
