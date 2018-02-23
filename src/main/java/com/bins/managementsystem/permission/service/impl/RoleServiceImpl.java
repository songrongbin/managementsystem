package com.bins.managementsystem.permission.service.impl;

import com.bins.managementsystem.common.utils.LoggerUtils;
import com.bins.managementsystem.core.mybatis.BaseMybatisDao;
import com.bins.managementsystem.core.mybatis.page.Pagination;
import com.bins.managementsystem.permission.dto.RolePermissionAllocationDto;
import com.bins.managementsystem.permission.mapper.RoleMapper;
import com.bins.managementsystem.permission.model.Role;
import com.bins.managementsystem.permission.service.IRoleService;
import com.bins.managementsystem.shiro.token.manage.TokenManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by songrongbin on 2018/1/29.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseMybatisDao<RoleMapper> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> findRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public void initData() {

        roleMapper.initData();
    }

    @Override
    public List<Role> findNowAllPermission() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId", TokenManager.getUserId());
        return roleMapper.findNowAllPermission(map);
    }

    @Override
    public Pagination<Role> findPage(ModelMap modelMap, int pageNo, int pageSize) {
        return super.findPage(modelMap, pageNo, pageSize);
    }

    @Override
    public int insertSelective(Role role) {
        return roleMapper.insertSelective(role);
    }

    @Override
    public Map<String, Object> deleteRoleById(String ids) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            int count=0;
            String resultMsg = "删除成功。";
            String[] idArray = new String[]{};
            if(StringUtils.contains(ids, ",")){
                idArray = ids.split(",");
            }else{
                idArray = new String[]{ids};
            }

            c:for (String idx : idArray) {
                Long id = new Long(idx);
                if(new Long(1).equals(id)){
                    resultMsg = "操作成功，But'系统管理员不能删除。";
                    continue c;
                }else{
                    count+=this.deleteByPrimaryKey(id);
                }
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
            resultMap.put("resultMsg", resultMsg);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

    @Override
    public Pagination<RolePermissionAllocationDto> findRoleAndPermissionPage(ModelMap modelMap, Integer pageNo,
            int pageSize) {
        return super.findPage("findRoleAndPermission", "findCount", modelMap, pageNo, pageSize);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}
