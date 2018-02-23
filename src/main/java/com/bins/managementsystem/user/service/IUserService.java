package com.bins.managementsystem.user.service;

import com.bins.managementsystem.core.mybatis.page.Pagination;
import com.bins.managementsystem.permission.dto.RoleDto;
import com.bins.managementsystem.permission.dto.UserRoleAllocationDto;
import com.bins.managementsystem.user.model.User;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * Created by songrongbin on 2018/1/29.
 */
public interface IUserService {

    User login(String username, String pswd);

    int updateByPrimaryKeySelective(User user);

    User findUserByEmail(String email);

    User insert(User entity);

    Pagination<User> findByPage(ModelMap map, Integer pageNo, int pageSize);

    Map<String,Object> deleteUserById(String ids);

    Map<String,Object> updateForbidUserById(Long id, Integer status);

    User selectByPrimaryKey(Long id);

    Pagination<UserRoleAllocationDto> findUserAndRole(ModelMap modelMap, Integer pageNo, int pageSize);

    List<RoleDto> selectRoleByUserId(Long id);

    Map<String, Object> addRole2User(Long userId, String ids);

    Map<String, Object> deleteRoleByUserIds(String userIds);
}
