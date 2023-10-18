package com.nfc.manager.nfc_manager.entity.DTO;

import com.nfc.manager.nfc_manager.entity.OrdersEntity;
import com.nfc.manager.nfc_manager.entity.UserRoles;
import com.nfc.manager.nfc_manager.entity.UserURL_TEST;
import com.nfc.manager.nfc_manager.utils.validator.UniqueUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public class UserDTO {

    @Size(min = 5, message = "Username must be at least 5 characters long.")
    @NotBlank(message = "Should contain username")
    @UniqueUsername
    private String username;

    @NotBlank(message = "Should contain password")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$",
            message = "Password must contain at least one letter and one number.")
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

    private List<OrdersDTO> orders;

    private List<UserRolesDTO> userRoles;

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public UserDTO setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UserDTO setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public UserDTO setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public UserDTO setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public List<OrdersDTO> getOrders() {
        return orders;
    }

    public UserDTO setOrders(List<OrdersDTO> orders) {
        this.orders = orders;
        return this;
    }

    public List<UserRolesDTO> getUserRoles() {
        return userRoles;
    }

    public UserDTO setUserRoles(List<UserRolesDTO> userRoles) {
        this.userRoles = userRoles;
        return this;
    }
}
