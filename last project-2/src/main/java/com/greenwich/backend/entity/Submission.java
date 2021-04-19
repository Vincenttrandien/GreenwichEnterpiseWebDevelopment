package com.greenwich.backend.entity;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

@Document("SUBMISSION")
public class Submission {
    @Id
    @Field("_id")
    private ObjectId id;
    private String name;

    private String codeSubmission;
    private String comment;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateSubmit;
    
    private boolean status;

    private String codeUser;

    private String nameFaculty;

    private String codeTopic;

    private String codeUserRecommend;

    private String nam;

    private byte[] data;

    private String type;




    public Submission() {
    }

    public Submission(String name, String codeSubmission, String comment, Date dateSubmit, boolean status, String codeUser, String nameFaculty, String codeTopic,String codeUserRecommend ,String nam, byte[] data, String type) {
        this.name = name;
        this.codeSubmission = codeSubmission;
        this.comment = comment;
        this.dateSubmit = dateSubmit;
        this.status = status;
        this.codeUser = codeUser;
        this.nameFaculty = nameFaculty;
        this.codeTopic = codeTopic;
        this.codeUserRecommend = codeUserRecommend;
        this.nam = nam;
        this.data = data;
        this.type = type;
    }

    public Submission(String name, String type,byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getCodeTopic() {
        return codeTopic;
    }

    public void setCodeTopic(String codeTopic) {
        this.codeTopic = codeTopic;
    }

    public Date getDateSubmit() {
		return dateSubmit;
	}

	public void setDateSubmit(Date dateSubmit) {
		this.dateSubmit = dateSubmit;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

    public String getCodeSubmission() {
        return codeSubmission;
    }

    public void setCodeSubmission(String codeSubmission) {
        this.codeSubmission = codeSubmission;
    }

    public String getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(String codeUser) {
        this.codeUser = codeUser;
    }

    public String getNameFaculty() {
        return nameFaculty;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }


    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }


    public String getCodeUserRecommend() {
        return codeUserRecommend;
    }

    public void setCodeUserRecommend(String codeUserRecommend) {
        this.codeUserRecommend = codeUserRecommend;
    }
}
