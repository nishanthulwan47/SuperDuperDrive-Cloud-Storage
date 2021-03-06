package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

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
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPass = hashService.getHashedValue(user.getPassword(), encodedSalt);

        return userMapper.insert(user.getUsername(), encodedSalt, hashedPass, user.getFirstName(), user.getLastName());
    }

    public AppUser getUser(String username) {
        return userMapper.getUser(username);
    }
}
