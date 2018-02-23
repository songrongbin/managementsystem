package com.bins.managementsystem.permission.model;

import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONObject;

import java.io.Serializable;

@Setter
@Getter
public class RolePermission implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long rid;
    private Long pid;

    public RolePermission() {
	}
    public RolePermission(Long rid,Long pid) {
    	this.rid = rid;
    	this.pid = pid;
    }

    public String toString(){
    	return JSONObject.fromObject(this).toString();
    }
}
