package com.bins.managementsystem.user.service.impl;

import com.bins.managementsystem.common.utils.LoggerUtils;
import com.bins.managementsystem.core.mybatis.BaseMybatisDao;
import com.bins.managementsystem.core.mybatis.page.Pagination;
import com.bins.managementsystem.permission.dto.RoleDto;
import com.bins.managementsystem.permission.dto.UserRoleAllocationDto;
import com.bins.managementsystem.permission.mapper.UserRoleMapper;
import com.bins.managementsystem.permission.model.UserRole;
import com.bins.managementsystem.shiro.session.CustomSessionManager;
import com.bins.managementsystem.shiro.token.manage.TokenManager;
import com.bins.managementsystem.user.mapper.UserMapper;
import com.bins.managementsystem.user.model.User;
import com.bins.managementsystem.user.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songrongbin on 2018/1/29.
 */
@Service("userService")
public class UserServiceImpl extends BaseMybatisDao<UserMapper> implements IUserService {

    @Autowired
    private CustomSessionManager customSessionManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User login(String username, String pswd) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("email", username);
        map.put("pswd", pswd);
        User user = userMapper.login(map);
        if (user == null) {
            user = new User();
        }
        user.setId(11l);
        user.setEmail(username);
        user.setCreateTime(new Date());
        user.setNickname("123");
        user.setStatus(1);
        user.setPswd("4cbd2e7ba3252f82ff780e77d9e1eb3f");
        return user;
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User findUserByEmail(String email) {

        return userMapper.findUserByEmail(email);
    }

    @Override
    public User insert(User entity) {
        return null;
    }

    @Override
    public Pagination<User> findByPage(ModelMap resultMap, Integer pageNo, int pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public Map<String, Object> deleteUserById(String ids) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            int count=0;
            String[] idArray = new String[]{};
            if(StringUtils.contains(ids, ",")){
                idArray = ids.split(",");
            }else{
                idArray = new String[]{ids};
            }

            for (String id : idArray) {
                count+=this.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

    private int deleteByPrimaryKey(Long id) {

        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Pagination<UserRoleAllocationDto> findUserAndRole(ModelMap modelMap, Integer pageNo, int pageSize) {
        return super.findPage("findUserAndRole", "findCount", modelMap, pageNo, pageSize);
    }

    @Override
    public List<RoleDto> selectRoleByUserId(Long id) {

        return userMapper.selectRoleByUserId(id);
    }

    @Override
    public Map<String, Object> addRole2User(Long userId, String ids) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int count = 0;
        try {
            //先删除原有的。
            userRoleMapper.deleteByUserId(userId);
            //如果ids,role 的id 有值，那么就添加。没值象征着：把这个用户（userId）所有角色取消。
            if(StringUtils.isNotBlank(ids)){
                String[] idArray = null;

                //这里有的人习惯，直接ids.split(",") 都可以，我习惯这么写。清楚明了。
                if(StringUtils.contains(ids, ",")){
                    idArray = ids.split(",");
                }else{
                    idArray = new String[]{ids};
                }
                //添加新的。
                for (String rid : idArray) {
                    //这里严谨点可以判断，也可以不判断。这个{@link StringUtils 我是重写了的}
                    if(StringUtils.isNotBlank(rid)){
                        UserRole entity = new UserRole(userId,new Long(rid));
                        count += userRoleMapper.insertSelective(entity);
                    }
                }
            }
            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        } catch (Exception e) {
            resultMap.put("status", 200);
            resultMap.put("message", "操作失败，请重试！");
        }
        //清空用户的权限，迫使再次获取权限的时候，得重新加载
        TokenManager.clearUserAuthByUserId(userId);
        resultMap.put("count", count);
        return resultMap;
    }

    @Override
    public Map<String, Object> deleteRoleByUserIds(String userIds) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("userIds", userIds);
            userRoleMapper.deleteRoleByUserIds(resultMap);
            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        } catch (Exception e) {
            resultMap.put("status", 200);
            resultMap.put("message", "操作失败，请重试！");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> updateForbidUserById(Long id, Integer status) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            User user = selectByPrimaryKey(id);
            user.setStatus(status);
            updateByPrimaryKeySelective(user);

            //如果当前用户在线，需要标记并且踢出
            customSessionManager.forbidUserById(id,status);


            resultMap.put("status", 200);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败，请刷新再试！");
            LoggerUtils.fmtError(getClass(), "禁止或者激活用户登录失败，id[%s],status[%s]", id,status);
        }
        return resultMap;
    }


}
