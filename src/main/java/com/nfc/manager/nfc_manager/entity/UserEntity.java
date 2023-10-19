package com.nfc.manager.nfc_manager.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity{
    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String company;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false)
    private String country;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String postCode;

    @Column
    private String phoneNumber;

    @Column(nullable = false)
    private String contactEmail;

    @Column
    private LocalDateTime registrationDate;

    @Column(nullable = false)
    private Boolean softDeleted;

    @OneToOne(cascade = CascadeType.PERSIST)
    private UserURL_TEST userURLTEST;

    @OneToMany
    private List<NFC> nfcList;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoles> userRoles;


    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public UserEntity setCompany(String company) {
        this.company = company;
        return this;
    }


    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public UserEntity setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public UserURL_TEST getUserURL() {
        return userURLTEST;
    }

    public UserEntity setUserURL(UserURL_TEST userURLTEST) {
        this.userURLTEST = userURLTEST;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public UserEntity setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UserEntity setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public UserEntity setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public Boolean getSoftDeleted() {
        return softDeleted;
    }

    public UserEntity setSoftDeleted(Boolean softDeleted) {
        this.softDeleted = softDeleted;
        return this;
    }

    public List<NFC> getNfcList() {
        return nfcList;
    }

    public UserEntity setNfcList(List<NFC> nfcList) {
        this.nfcList = nfcList;
        return this;
    }
}
