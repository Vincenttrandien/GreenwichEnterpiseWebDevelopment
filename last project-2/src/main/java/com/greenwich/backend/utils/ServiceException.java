package com.greenwich.backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class ServiceException extends Exception{
    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private String actionType = "UPDATE";
    private String module = "ADMIN_TOOL_SERVICE";

    public ServiceException(String message) {
        super();
        this.message = message;
    }

    public ServiceException(String message, HttpStatus status) {
        super();
        this.message = message;
        this.status = status;
    }

    public ServiceException(String message, String actionType, HttpStatus status) {
        super();
        this.message = message;
        this.actionType = actionType;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}

