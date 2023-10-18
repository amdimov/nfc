package com.nfc.manager.nfc_manager.entity.views;

import com.nfc.manager.nfc_manager.entity.DTO.OrdersDTO;
import com.nfc.manager.nfc_manager.entity.DTO.UserRolesDTO;
import com.nfc.manager.nfc_manager.utils.validator.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class UserView {

    private String username;

    private String password;

    private String company;

    private String firstName;

    private String lastName;

    private String country;

    private String address;

    private String city;

    private String postCode;

    private String phoneNumber;

    private String contactEmail;

    private LocalDateTime registrationDate;

    public String getUsername() {
        return username;
    }

    public UserView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserView setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public UserView setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserView setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserView setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserView setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UserView setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserView setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public UserView setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public UserView setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

}
