package com.greenwich.backend.exception;

public enum NotFoundException {
    FACULTY_EXISTED("Faculty is existed"),
    FACULTY_NOT_FOUND("ERROR 404 : FACULTY NOT FOUND "),

    USER_EXISTED("User is existed"),
    USER_NOT_FOUND("ERROR 404 : USER NOT FOUND "),

    TOPIC_EXISTED("TOPIC is existed"),
    TOPIC_NOT_FOUND("ERROR 404 : TOPIC NOT FOUND "),
    TOPIC_HAD_FACULTY("TOPIC HAD FACULTY "),

    SUBMISSION_EXISTED("SUBMISSION is existed"),
    SUBMISSION_NOT_FOUND("ERROR 404 : SUBMISSION NOT FOUND "),

    COMMENT_NOT_FOUND("ERROR 404 : COMMENT NOT FOUND");


    private final String desc;

    private NotFoundException(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
