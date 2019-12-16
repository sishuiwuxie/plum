package com.ibeetl.admin.console.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.ibeetl.admin.console.web.dto.RoleDataAccessFunction;
import com.ibeetl.admin.core.entity.CoreRoleFunction;

@SqlResource("console.roleFunction")
public interface RoleFunctionConsoleDao extends BaseMapper<CoreRoleFunction> {


    void deleteRoleFunction(List<String> ids);

    List<String> getFunctionIdByRole(String roleId);

    List<RoleDataAccessFunction> getQueryFunctionAndRoleData(String roleId);


}
