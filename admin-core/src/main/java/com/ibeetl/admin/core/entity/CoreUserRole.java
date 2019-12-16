package com.ibeetl.admin.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.AssignID;

/*
 *  用户角色关系
 * gen by beetlsql 2016-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户角色关联")
public class CoreUserRole extends BaseEntity {
	
	// 自增id
	//@SeqID(name = "ORACLE_CORE_SEQ_NAME")
	@AssignID
	private String id;
	
	// 授权机构id
	@ApiModelProperty(value = "授权机构id")
	@NotNull(message = "授权机构不能为空")
	private String orgId;
	
	// 授权角色id
	@ApiModelProperty(value = "授权角色id")
	@NotNull(message = "授权角色不能为空")
	private String roleId;
	
	// 用户id
	@ApiModelProperty(value = "用户id")
	@NotNull(message = "授权用户不能为空")
	
	private String userId;
	@ApiModelProperty(value = "创建时间")
	protected Date createTime;
	
	
}
