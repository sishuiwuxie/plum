package com.ibeetl.admin.console.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.ibeetl.admin.console.web.query.RoleUserQuery;
import com.ibeetl.admin.core.entity.CoreRole;
import com.ibeetl.admin.core.entity.CoreUser;

@SqlResource("console.role")
public interface RoleConsoleDao extends BaseMapper<CoreRole> {

    /**
     * 根据条件分页查询
     * @param query 查询条件
     */
    void queryByCondition(PageQuery query);

    /**
     * 批量删除角色
     * @param ids 角色id
     */
    void batchDelByIds(List<String> ids);
    
    void batchDeleteRoleFunction(List<String> ids);
    void batchDeleteRoleMenu(List<String> ids);
    void batchDeleteUserRole(List<String> ids);
    
    
    PageQuery<CoreUser>  queryUser(PageQuery query);
    


   
}
