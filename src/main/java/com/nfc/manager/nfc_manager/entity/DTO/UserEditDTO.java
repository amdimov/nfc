package com.nfc.manager.nfc_manager.entity.DTO;

import com.nfc.manager.nfc_manager.utils.validator.UniqueUsername;
import com.nfc.manager.nfc_manager.utils.validator.classLevel.UniqueUsernameNotId;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@UniqueUsernameNotId
public class UserEditDTO {
    private Long id;

    @Size(min = 5, message = "Username must be at least 5 characters long.")
    @NotBlank(message = "Should contain username")
    private String username;

    @Pattern(regexp = "^(?:(?=.*[a-zA-Z])(?=.*\\d).+|)$",
            message = "Password must contain at least one letter and one number or be empty.")
    private String password;

    @NotBlank(message = "Should contain company")
    private String company;

    private String firstName;

    private String lastName;

    @NotBlank(message = "Should contain country")
    private String country;

    private String address;

    private String city;

    private String postCode;

    private String phoneNumber;

    @NotBlank(message = "If email is the same as username, please rewrite it here")
    @Email
    private String contactEmail;

    private LocalDateTime registrationDate;

    public Long getId() {
        return id;
    }

    public UserEditDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEditDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEditDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public UserEditDTO setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEditDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserEditDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserEditDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserEditDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UserEditDTO setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEditDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public UserEditDTO setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public UserEditDTO setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
