package com.ibeetl.admin.core.web.dto;

import lombok.Data;

@Data
public class SystemMenuView {

	private String code;
	private String id;
	private String name;
	private String icon;

	public SystemMenuView(String id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

}
