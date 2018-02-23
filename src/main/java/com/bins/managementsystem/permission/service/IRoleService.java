package com.bins.managementsystem.permission.service;

import com.bins.managementsystem.core.mybatis.page.Pagination;
import com.bins.managementsystem.permission.dto.RolePermissionAllocationDto;
import com.bins.managementsystem.permission.model.Role;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by songrongbin on 2018/1/29.
 */
public interface IRoleService {

    int deleteByPrimaryKey(Long id);

    Set<String> findRoleByUserId(Long userId);

    void initData();

    List<Role> findNowAllPermission();

    Pagination<Role> findPage(ModelMap modelMap, int pageNo, int pageSize);

    int insertSelective(Role role);

    Map<String,Object> deleteRoleById(String ids);

    Pagination<RolePermissionAllocationDto> findRoleAndPermissionPage(ModelMap modelMap, Integer pageNo, int pageSize);
}
