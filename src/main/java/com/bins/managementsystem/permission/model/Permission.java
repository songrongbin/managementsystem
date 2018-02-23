package com.bins.managementsystem.permission.model;

import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONObject;

@Setter
@Getter
public class Permission {
    private Long id;

    private String url;

    private String name;

    public String toString(){
        return JSONObject.fromObject(this).toString();
    }

}
