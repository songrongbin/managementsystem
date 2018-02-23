package com.bins.managementsystem.user.mapper;

import com.bins.managementsystem.permission.dto.RoleDto;
import com.bins.managementsystem.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(Map<String, Object> map);

    User findUserByEmail(String email);

    List<RoleDto> selectRoleByUserId(Long id);
}
