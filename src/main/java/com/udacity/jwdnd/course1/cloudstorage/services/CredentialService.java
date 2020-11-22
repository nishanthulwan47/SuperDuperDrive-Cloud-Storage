package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    private EncryptionService encryptionService;

    public CredentialService(EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CredentialService Bean");
    }

    public void addCredential(Credential credential) {
        String key = this.encryptionService.generateKey();
        credential.setKey(key);
        credential.setPassword(this.encryptPassword(credential));
        this.credentialMapper.insert(credential);
    }

    public List<Credential> getAllCredentials() {
        return this.credentialMapper.getAllCredentials();
    }

    public List<Credential> getCredentialByUserId(Integer id) {
        return this.credentialMapper.getAllCredentialByUserId(id);
    }

    public Credential getCredentialById(Integer id) {
        return this.credentialMapper.getCredentialId(id);
    }

    public void deleteCredential(Integer id) {
        this.credentialMapper.delete(id);
    }

    public String retrieveKeyByCredentialId(Integer id) {
        return this.credentialMapper.retrieveKeyByCredentialId(id);
    }

    public void editCredential(Credential credential) {
        String key = this.credentialMapper.retrieveKeyByCredentialId(credential.getCredentialid());
        String encodedPassword = this.encryptionService.encryptValue(credential.getPassword(), key);
        credential.setPassword(encodedPassword);
        this.credentialMapper.update(credential);
    }

    public String encryptPassword(Credential credential) {
        return this.encryptionService.encryptValue(credential.getPassword(), credential.getKey());
    }
}
