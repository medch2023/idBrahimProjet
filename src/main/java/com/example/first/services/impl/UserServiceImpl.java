package com.example.first.services.impl;

import com.example.first.entities.UserEntity;
import com.example.first.repositories.UserRepository;
import com.example.first.services.UserService;
import com.example.first.shred.dto.UserDto;
import com.example.first.shred.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity checkUser = userRepository.findByEmail(user.getEmail());
        if (checkUser != null) throw new RuntimeException("User al ready exesit");
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user , userEntity);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generateUserId(32));
        UserEntity newUser = userRepository.save(userEntity);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(newUser , userDto);

        return userDto;
    }
}
