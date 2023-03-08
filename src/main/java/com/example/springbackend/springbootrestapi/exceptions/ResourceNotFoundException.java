package com.example.springbackend.springbootrestapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resName,String fName,long FValue){
       
        this.resourceName=resName;
        this.fieldName=fName;
        this.fieldValue=FValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
