package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name ="users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = 2, message = "First name must not be less than 2 characters")
    private String firstName;

    private String middleName;

    @NotNull(message = "Last name cannot be missing or empty")
    @Size(min = 2, message = "Last name must not be less than 2 characters")
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    private String salt;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    private String phoneNumber;

    private Long createdAt;

    private Long updatedAt;

    public AppUser(Integer userid, String username, @NotNull @Size(min = 2, message = "First name must not be less than 2 characters") String firstName, String middleName, @NotNull(message = "Last name cannot be missing or empty") @Size(min = 2, message = "Last name must not be less than 2 characters") String lastName, @NotEmpty String password, @NotEmpty String confirmPassword, String salt, Date dateOfBirth, String phoneNumber, Long createdAt, Long updatedAt) {
        this.userid = userid;
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.salt = salt;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
