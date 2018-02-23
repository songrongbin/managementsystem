package com.bins.managementsystem.permission.mapper;

import com.bins.managementsystem.permission.model.UserRole;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

	int deleteByUserId(Long id);

	int deleteRoleByUserIds(Map<String, Object> resultMap);

	List<Long> findUserIdByRoleId(Long id);
}
