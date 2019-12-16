package com.ibeetl.admin.core.web.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class MenuNodeView {

	private String name;
	private String code;
	private String id;
	private String path;
	private String icon;
	private List<MenuNodeView> children = new ArrayList<MenuNodeView>();


}
