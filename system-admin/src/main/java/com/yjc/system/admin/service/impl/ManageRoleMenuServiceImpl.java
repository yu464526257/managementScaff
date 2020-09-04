package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.ManageMenuMapper;
import com.yjc.system.admin.dao.ManageRoleMapper;
import com.yjc.system.admin.dao.ManageRoleMenuMapper;
import com.yjc.system.admin.dto.RoleMenuDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageMenu;
import com.yjc.system.admin.entity.ManageRole;
import com.yjc.system.admin.entity.ManageRoleMenu;
import com.yjc.system.admin.entity.ManageUserRule;
import com.yjc.system.admin.service.ManageRoleMenuService;
import com.yjc.system.commen.common.utils.CheckSpecial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 角色跟老得岗位对应
 *
 * @author yjc
 * @date 2020-07-10 15:50:38
 */
@Service("manageRoleMenuService")
public class ManageRoleMenuServiceImpl extends ServiceImpl<ManageRoleMenuMapper, ManageRoleMenu> implements ManageRoleMenuService {

  @Autowired(required = false)
  private ManageRoleMenuMapper manageRoleMenuMapper;
  @Autowired(required = false)
  private ManageMenuMapper manageMenuMapper;
  @Autowired(required = false)
  private ManageRoleMapper manageRoleMapper;

    /**
   * 角色跟老得岗位对应简单分页查询
   * @param manageRoleMenu 角色跟老得岗位对应
   * @return
   */
  @Override
  public IPage<ManageRoleMenu> getManageRoleMenuPage(Page<ManageRoleMenu> page, ManageRoleMenu manageRoleMenu){
      return baseMapper.getManageRoleMenuPage(page,manageRoleMenu);
  }


//==============================================
    /**
     * 新增角色对应菜单关联表（内部自带方法）
     * @param manageRoleMenu  角色对应菜单关联表
     * @return
     */
    @Override
    public ResultEntity save(ManageRoleMenu manageRoleMenu) {
        if(manageRoleMenu.getMenuId()!=null){
            ManageMenu manageMenuByMenuId = manageMenuMapper.getManageMenuByMenuId(manageRoleMenu.getMenuId());
            System.out.println(manageRoleMenu.getRoleId());
            ManageRole manageRoleById = manageRoleMapper.getManageRoleById(manageRoleMenu.getRoleId());
            if(manageMenuByMenuId!=null&&manageRoleById!=null){
                manageRoleMenu.setMenuName(manageMenuByMenuId.getMenuName());
                manageRoleMenu.setUrl(manageMenuByMenuId.getUrl());
                manageRoleMenu.setRoleName(manageRoleById.getRoleName());
                Integer result = baseMapper.insert(manageRoleMenu);
                if(result>0){
                    return ResultEntity.ok(result,"新增成功");
                }else{
                    return ResultEntity.failed(result,"新增失败");
                }
            }
        }
        return ResultEntity.failed("新增失败");
    }

    /**
     *修改角色对应菜单关联表
     * @param manageRoleMenu 角色对应菜单关联表
     * @return
     */
    @Override
    public ResultEntity modify(ManageRoleMenu manageRoleMenu) {
        if(manageRoleMenu.getMenuId()!=null){
            ManageMenu manageMenuByMenuId = manageMenuMapper.getManageMenuByMenuId(manageRoleMenu.getMenuId());
            ManageRole manageRoleById = manageRoleMapper.getManageRoleById(manageRoleMenu.getRoleId());
            if(manageMenuByMenuId!=null){
                manageRoleMenu.setMenuName(manageMenuByMenuId.getMenuName());
                manageRoleMenu.setUrl(manageMenuByMenuId.getUrl());
                manageRoleMenu.setRoleName(manageRoleById.getRoleName());
                Integer modify = manageRoleMenuMapper.modify(manageRoleMenu);
                if(modify>0){
                    return ResultEntity.ok(modify,"修改成功");
                }else{
                    return ResultEntity.failed(modify,"修改失败");
                }
            }
        }
        return ResultEntity.failed("修改失败");
    }

    /**
     *删除角色对应菜单关联表
     * @param roleId  角色编号
     * @param menuId  菜单编号
     * @return
     */
    @Override
    public ResultEntity remove(String  roleId, Long menuId) {
        if(roleId!=null&&menuId!=null){
            Integer remove = manageRoleMenuMapper.remove(roleId, menuId);
            if(remove>0){
                return ResultEntity.ok(remove,"删除成功");
            }else{
                return ResultEntity.failed(remove,"删除失败");
            }
        }
        return ResultEntity.failed("删除失败");
    }

    @Override
    public List<ManageRoleMenu> getRoleMenusByRole(List<ManageUserRule> list) {
        if(!CheckSpecial.listCheck(list)){
            return null;
        }
        try {
            List<String> roles = list.stream().map(p -> p.getRoleId()).collect(Collectors.toList());
            List<ManageRoleMenu> ls = baseMapper.getRoleMenusByRole(roles);
            return ls;
        }catch ( Exception e){
            return  null;
        }
    }

    /**
     * 根据角色id集合，查询角色菜单信息
     * @param roles
     * @return
     */
    @Override
    public ResultEntity getMenuDtoByRoles(List<String> roles) {
        ResultEntity resultEntity=new ResultEntity();
        List<RoleMenuDto> menuInfoList = manageRoleMenuMapper.getMenuDtoByRoles(roles);
        //查出所有菜单
        List<RoleMenuDto> parList = new ArrayList();
        List<RoleMenuDto> sonList = new ArrayList();
        for (int i = 0; i < menuInfoList.size(); i++) {
            if (menuInfoList.get(i).getMenuParentId() == 0) {
                RoleMenuDto menuDto = new RoleMenuDto();
                menuDto.setRoleId(menuInfoList.get(i).getRoleId());
                menuDto.setRoleName(menuInfoList.get(i).getRoleName());
                menuDto.setPositionIndex(menuInfoList.get(i).getPositionIndex());
                menuDto.setMenuType(menuInfoList.get(i).getMenuType());
                menuDto.setMenuParentId(menuInfoList.get(i).getMenuParentId());
                menuDto.setMenuName(menuInfoList.get(i).getMenuName());
                menuDto.setMenuId(menuInfoList.get(i).getMenuId());
                menuDto.setIcon(menuInfoList.get(i).getIcon());
                menuDto.setUrl(menuInfoList.get(i).getUrl());
                parList.add(menuDto);
            }
        }

        List list = new ArrayList();
        for (int l = 0; l < parList.size(); l++) {
            List list1 = new ArrayList();
            for (int k = 0; k < sonList.size(); k++) {
                if (parList.get(l).getMenuParentId().equals(sonList.get(k).getMenuId())) {
                    list1.add(sonList.get(k));
                }
            }
            parList.get(l).setChild(list1);
        }
        list.add(parList);
        resultEntity.setMsg("查询成功");
        resultEntity.setData(list);
        resultEntity.setCode(200);
        return resultEntity;
    }





    /**
     * Tree树列表
     * @param roles
     * @return
     */
    @Override
    public List<RoleMenuDto> getList(List<String> roles) {
        List<RoleMenuDto> listNode = new ArrayList<RoleMenuDto>();
        List<RoleMenuDto> menuInfoList = manageRoleMenuMapper.getMenuDtoByRoles(roles);
        // 先找到所有的一级菜单
        for (int i = 0; i < menuInfoList.size(); i++) {
            if(menuInfoList.get(i).getMenuArray()!=""&&menuInfoList.get(i).getMenuArray()!=null) {
                String bmArray = menuInfoList.get(i).getMenuArray();
                String replace = bmArray.replace("[", "").replace("]", "");
                String[] split = replace.split(",");
                List<Integer> array = new ArrayList<>();
                for (int j = 0; j < split.length; j++) {
                    if (!split[j].equals("") && split[j] != null) {
                        array.add(Integer.parseInt(split[j]));
                    }
                }
                menuInfoList.get(i).setArray(array);
            }
            // 一级菜单父ID为0
            Long parentId = menuInfoList.get(i).getMenuParentId();
            if (parentId == 0) {
                listNode.add(menuInfoList.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (RoleMenuDto sysDepts : listNode) {
            if(sysDepts.getMenuArray()!=""&&sysDepts.getMenuArray()!=null) {
                String bmArray = sysDepts.getMenuArray();
                String replace = bmArray.replace("[", "").replace("]", "");
                String[] split = replace.split(",");
                List<Integer> array = new ArrayList<>();
                for (int j = 0; j < split.length; j++) {
                    if (!split[j].equals("") && split[j] != null) {
                        array.add(Integer.parseInt(split[j]));
                    }
                }
                sysDepts.setArray(array);
            }
            List<RoleMenuDto> childList = getChildList(sysDepts.getMenuId(), menuInfoList);
            sysDepts.setChild(childList);
        }
        return listNode;
    }

    /**
     * 根据单个角色编号查询菜单列表
     * @param roleId
     * @return
     */
    @Override
    public List<RoleMenuDto> getMenuDtoByRoleId(String roleId) {
        List<RoleMenuDto> listNode = new ArrayList<RoleMenuDto>();
        List<RoleMenuDto> menuInfoList = manageRoleMenuMapper.getMenuDtoByRoleId(roleId);
        // 先找到所有的一级菜单
        for (int i = 0; i < menuInfoList.size(); i++) {
            if(menuInfoList.get(i).getMenuArray()!=""&&menuInfoList.get(i).getMenuArray()!=null) {
                String bmArray = menuInfoList.get(i).getMenuArray();
                String replace = bmArray.replace("[", "").replace("]", "");
                String[] split = replace.split(",");
                List<Integer> array = new ArrayList<>();
                for (int j = 0; j < split.length; j++) {
                    if (!split[j].equals("") && split[j] != null) {
                        array.add(Integer.parseInt(split[j]));
                    }
                }
                menuInfoList.get(i).setArray(array);
            }
            // 一级菜单父ID为0
            Long parentId = menuInfoList.get(i).getMenuParentId();
            if (parentId == 0) {
                listNode.add(menuInfoList.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (RoleMenuDto sysDepts : listNode) {
            if(sysDepts.getMenuArray()!=""&&sysDepts.getMenuArray()!=null) {
                String bmArray = sysDepts.getMenuArray();
                String replace = bmArray.replace("[", "").replace("]", "");
                String[] split = replace.split(",");
                List<Integer> array = new ArrayList<>();
                for (int j = 0; j < split.length; j++) {
                    if (!split[j].equals("") && split[j] != null) {
                        array.add(Integer.parseInt(split[j]));
                    }
                }
                sysDepts.setArray(array);
            }
            List<RoleMenuDto> childList = getChildList(sysDepts.getMenuId(), menuInfoList);
            sysDepts.setChild(childList);
        }
        return listNode;
    }

    public List<RoleMenuDto> getChildList(Long id,List<RoleMenuDto> sysDeptss) {
        // 子菜单
        List<RoleMenuDto> SysDeptsList = new ArrayList<>();
        for (RoleMenuDto sysDepts : sysDeptss) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (sysDepts.getMenuParentId().equals(id)) {
                SysDeptsList.add(sysDepts);
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (RoleMenuDto sysDepts : SysDeptsList) {
            sysDepts.setChild(getChildList(sysDepts.getMenuId(),sysDeptss));
        }

        // 判断递归结束
        if (SysDeptsList.size() == 0) {
            return null;
        }
        return SysDeptsList;
    }

}
