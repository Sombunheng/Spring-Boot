package com.bunheng.java.learn.phoneshop.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{
    private final String resourceName;
    private final Integer id;

    public ResourceNotFoundException(String resourceName, Integer id) {
        super(HttpStatus.NOT_FOUND , String.format("%s with id %d not found", resourceName , id));
        this.id = id ;
        this.resourceName = resourceName ;

    }

    public Object getResourceName() {
        return resourceName;
    }

    public Integer getResourceId() {
        return id;
    }
    
}
