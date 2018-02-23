package com.bins.managementsystem.permission.model;

import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONObject;

import java.io.Serializable;

@Setter
@Getter
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long uid;
    private Long rid;

    public UserRole(Long uid,Long rid) {
    	this.uid = uid;
    	this.rid = rid;
	}
    public UserRole() {
    }
    public String toString(){
    	return JSONObject.fromObject(this).toString();
    }
}
