package com.ibeetl.admin.console.web.dto;

import com.ibeetl.admin.core.entity.CoreFunction;

public class RoleDataAccessFunction extends CoreFunction {
	private Integer dataAccessType;
	private String roleId;

	public Integer getDataAccessType() {
		return dataAccessType;
	}

	public void setDataAccessType(Integer dataAccessType) {
		this.dataAccessType = dataAccessType;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
