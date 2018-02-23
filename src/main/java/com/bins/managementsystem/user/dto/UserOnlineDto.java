package com.bins.managementsystem.user.dto;

import com.bins.managementsystem.user.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class UserOnlineDto extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public UserOnlineDto(User user) {
		super(user);
	}

    //Session Id
	private String sessionId;
	//Session Host
	private String host;
	//Session创建时间
	private Date startTime;
	//Session最后交互时间
	private Date lastAccess;
	//Session timeout
	private long timeout;
	//session 是否踢出
	private boolean sessionStatus = Boolean.TRUE;



}
