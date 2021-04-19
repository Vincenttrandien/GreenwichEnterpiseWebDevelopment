package com.greenwich.backend.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String codeUser;
    private String name;
    private String email;
    private String dob;
    private String address;
    private String phoneNumber;
    private String nam;
    private String username;
    private String facultyName;
    private List<String> roles;

    public JwtResponse(String accessToken, String id,
                       String codeUser, String name, String email, String dob,
                       String address, String phoneNumber , String nam, String username, String facultyName,List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.codeUser = codeUser;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nam = nam;
        this.username = username;
        this.facultyName = facultyName;
        this.roles = roles;
    }



    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(String codeUser) {
        this.codeUser = codeUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}

