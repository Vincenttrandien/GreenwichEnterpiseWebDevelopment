package com.greenwich.backend.response;

import java.util.Date;

public class FileResponse {
    private String id;
    private String name;
    private String url;
    private String type;
    private String codeSubmission;
    private String comment;
    private Date dateSubmit;
    private boolean status;
    private String codeUser;
    private String nameFaculty;
    private String codeTopic;
    private String codeUserRecommend;
    private String nam;
    private long size;

//    public FileResponse(String name, String url, String type,long size) {
//        this.name = name;
//        this.url = url;
//        this.type = type;
//        this.size = size;
//    }


    public FileResponse(String id, String name, String url, String type,
                        String codeSubmission, String comment, Date dateSubmit,
                        boolean status, String codeUser, String nameFaculty,String codeTopic,String codeUserRecommend,String nam, long size) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.codeSubmission = codeSubmission;
        this.comment = comment;
        this.dateSubmit = dateSubmit;
        this.status = status;
        this.codeUser = codeUser;
        this.nameFaculty = nameFaculty;
        this.codeTopic = codeTopic;
        this.codeUserRecommend = codeUserRecommend;
        this.nam = nam;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeSubmission() {
        return codeSubmission;
    }

    public void setCodeSubmission(String codeSubmission) {
        this.codeSubmission = codeSubmission;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
