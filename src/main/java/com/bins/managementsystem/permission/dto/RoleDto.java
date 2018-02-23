package com.bins.managementsystem.permission.dto;

import com.bins.managementsystem.common.utils.StringUtils;

import java.io.Serializable;

/**
 * Created by songrongbin on 2018/2/23.
 */
public class RoleDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID (用String， 考虑多个ID，现在只有一个ID)
     */
    private String userId;
    /**
     * 是否勾选
     */
    private String marker;

    public boolean isCheck(){
        return StringUtils.equals(userId,marker);
    }
    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
