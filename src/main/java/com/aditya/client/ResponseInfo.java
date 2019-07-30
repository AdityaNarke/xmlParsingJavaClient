package com.aditya.client;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseInfo implements Serializable {
	private String status;
    private String message;
    
    @Override
    public String toString() {
    	return this.status + "/" + this.message;
    }
}
