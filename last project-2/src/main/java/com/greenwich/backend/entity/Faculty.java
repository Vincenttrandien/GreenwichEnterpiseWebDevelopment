package com.greenwich.backend.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document("FACULTY")
public class Faculty {
    @Id
    @Field("_id")
    private ObjectId id;
    private String codeFaculty;
    private String name;
    private String desc;
    private String nam;

    private ArrayList<Topic> topic = new ArrayList<>();

    private ArrayList<User> users = new ArrayList<>();


    public String getCodeFaculty() {
        return codeFaculty;
    }

    public void setCodeFaculty(String codeFaculty) {
        this.codeFaculty = codeFaculty;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public ArrayList<Topic> getTopic() {
        return topic;
    }

    public void setTopic(ArrayList<Topic> topic) {
        this.topic = topic;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
