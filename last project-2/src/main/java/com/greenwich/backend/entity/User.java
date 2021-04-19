package com.greenwich.backend.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document("USER")
public class User {

    @Id
    @Field("_id")
    private ObjectId id;
    private String codeUser;
    private String name;
    private String email;
    private String dob;
    private String address;
    private String phoneNumber;
    private String nam;

    private String username;

    private String password;

    private String retypePassword;

    private String facultyName;

    private ArrayList<Topic> topic = new ArrayList<>();

    private ArrayList<Submission> submissions = new ArrayList<>();

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String codeUser, String name, String email,
                String dob, String address, String phoneNumber,
                String nam,String facultyName, String username, String password, String retypePassword) {
        this.codeUser = codeUser;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nam = nam;
        this.facultyName = facultyName;
        this.username = username;
        this.password = password;
        this.retypePassword = retypePassword;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public ArrayList<Topic> getTopic() {
        return topic;
    }

    public void setTopic(ArrayList<Topic> topic) {
        this.topic = topic;
    }

    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(ArrayList<Submission> submissions) {
        this.submissions = submissions;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }
}
