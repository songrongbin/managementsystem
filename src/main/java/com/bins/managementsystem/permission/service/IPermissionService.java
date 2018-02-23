package com.bins.managementsystem.permission.service;

import com.bins.managementsystem.core.mybatis.page.Pagination;
import com.bins.managementsystem.permission.dto.PermissionDto;
import com.bins.managementsystem.permission.model.Permission;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by songrongbin on 2018/1/29.
 */
public interface IPermissionService {
    int deleteByPrimaryKey(Long id);

    Permission insert(Permission record);

    Permission insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    Map<String, Object> deletePermissionById(String ids);

    Pagination<Permission> findPage(Map<String,Object> resultMap, Integer pageNo,
            Integer pageSize);
    List<PermissionDto> selectPermissionById(Long id);

    Map<String, Object> addPermission2Role(Long roleId,String ids);

    Map<String, Object> deleteByRids(String roleIds);
    //根据用户ID查询权限（permission），放入到Authorization里。
    Set<String> findPermissionByUserId(Long userId);
}
