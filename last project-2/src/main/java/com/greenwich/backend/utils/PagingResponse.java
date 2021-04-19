package com.greenwich.backend.utils;

import java.util.List;

public class PagingResponse<T> {
    private List<T> content;
    private long totalElements;

    public PagingResponse() {
    }

    public PagingResponse(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
