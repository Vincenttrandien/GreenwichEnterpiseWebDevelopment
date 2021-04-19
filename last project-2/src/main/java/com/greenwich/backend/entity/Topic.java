package com.greenwich.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document("TOPIC")
public class Topic {

    @Id
    @Field("_id")
    private ObjectId id;
    private String codeTopic;
    private String name;
    private String desc;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateUpdate;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateUpdate2;

    private String nam;

    private String nameFile;

    private byte[] data;

    private String type;

    private String nameFaculty;

    public Topic(String nameFile,String type,byte[] data) {
        this.nameFile = nameFile;
        this.type = type;
        this.data = data;
    }


    public String getNameFaculty() {
        return nameFaculty;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCodeTopic() {
        return codeTopic;
    }

    public void setCodeTopic(String codeTopic) {
        this.codeTopic = codeTopic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateUpdate2() {
        return dateUpdate2;
    }

    public void setDateUpdate2(Date dateUpdate2) {
        this.dateUpdate2 = dateUpdate2;
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

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
