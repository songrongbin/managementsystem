package com.bins.managementsystem.permission.mapper;

import com.bins.managementsystem.permission.dto.PermissionDto;
import com.bins.managementsystem.permission.model.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

	List<PermissionDto> selectPermissionById(Long id);
	//根据用户ID获取权限的Set集合
	Set<String> findPermissionByUserId(Long id);
}
