package com.ibeetl.admin.console.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.web.query.PageParam;

/**
 */
public class UserRoleQuery extends PageParam {

   
	@Query(name="角色",display=true,type=Query.TYPE_CONTROL,control="role")
    private String roleId;
    @Query(name="部门",display=true,type=Query.TYPE_CONTROL,control="org")
    private String orgId;

    private String userId;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
