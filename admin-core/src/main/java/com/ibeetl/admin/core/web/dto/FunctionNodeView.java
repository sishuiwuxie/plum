package com.ibeetl.admin.core.web.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class FunctionNodeView {

	private String name;
	private String code;
	private String accessUrl;
	private String id;

	private String icon;
	private List<FunctionNodeView> children=new ArrayList<FunctionNodeView>();

}
