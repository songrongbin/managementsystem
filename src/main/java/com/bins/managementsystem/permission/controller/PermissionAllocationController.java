package com.bins.managementsystem.permission.controller;

import com.bins.managementsystem.common.controller.BaseController;
import com.bins.managementsystem.core.mybatis.page.Pagination;
import com.bins.managementsystem.permission.dto.PermissionDto;
import com.bins.managementsystem.permission.dto.RolePermissionAllocationDto;
import com.bins.managementsystem.permission.service.IPermissionService;
import com.bins.managementsystem.permission.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by songrongbin on 2018/2/23.
 */
@Controller
@Scope(value="prototype")
@RequestMapping("permission")
public class PermissionAllocationController extends BaseController {
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRoleService roleService;
    /**
     * 权限分配
     * @param modelMap
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value="allocation")
    public ModelAndView allocation(ModelMap modelMap,Integer pageNo,String findContent){
        modelMap.put("findContent", findContent);
        Pagination<RolePermissionAllocationDto> boPage = roleService.findRoleAndPermissionPage(modelMap,pageNo,pageSize);
        modelMap.put("page", boPage);
        return new ModelAndView("permission/allocation");
    }

    /**
     * 根据角色ID查询权限
     * @param id
     * @return
     */
    @RequestMapping(value="selectPermissionById")
    @ResponseBody
    public List<PermissionDto> selectPermissionById(Long id){
        List<PermissionDto> permissionBos = permissionService.selectPermissionById(id);
        return permissionBos;
    }
    /**
     * 操作角色的权限
     * @param roleId 	角色ID
     * @param ids		权限ID，以‘,’间隔
     * @return
     */
    @RequestMapping(value="addPermission2Role")
    @ResponseBody
    public Map<String,Object> addPermission2Role(Long roleId,String ids){
        return permissionService.addPermission2Role(roleId,ids);
    }
    /**
     * 根据角色id清空权限。
     * @param roleIds	角色ID ，以‘,’间隔
     * @return
     */
    @RequestMapping(value="clearPermissionByRoleIds")
    @ResponseBody
    public Map<String,Object> clearPermissionByRoleIds(String roleIds){
        return permissionService.deleteByRids(roleIds);
    }
}
