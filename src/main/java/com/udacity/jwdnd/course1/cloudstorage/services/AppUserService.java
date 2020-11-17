package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.security.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AppUserService {

    private final UserMapper userMapper;
    private final HashService hashService;
    private final EncryptionService encryptionService;


    private Logger logger = LoggerFactory.getLogger(AppUserService.class);

    public AppUserService(UserMapper userMapper, HashService hashService, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int saveAppUser(AppUser user) {
        user.setCreatedAt(System.currentTimeMillis());
        user.setUpdatedAt(System.currentTimeMillis());
        SecureRandom random = new SecureRandom();
        String salt = String.valueOf(System.currentTimeMillis());
        user.setSalt(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), salt);
        String encryptedPassword = encryptionService.encryptValue(hashedPassword, Constants.ENCRYPT_KEY);
        user.setPassword(encryptedPassword);
        return userMapper.insert(user);
    }

    public AppUser getUser(String username) {
        return userMapper.getUser(username);
    }
}
