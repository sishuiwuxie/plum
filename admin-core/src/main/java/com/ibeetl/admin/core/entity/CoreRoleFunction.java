package com.ibeetl.admin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibeetl.admin.core.util.ValidateConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.AssignID;

/**
 *
 * gen by beetlsql 2016-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "角色功能对应")
public class CoreRoleFunction extends BaseEntity {
	
	@NotNull(message = "ID不能为空", groups = ValidateConfig.UPDATE.class)
	//@SeqID(name = ORACLE_CORE_SEQ_NAME)
	@AssignID
	protected String id;
	// 删除标识
	@JsonIgnore
	@ApiModelProperty(value = "删除标识")
	protected Integer delFlag = 0;
	// 创建时间
	@ApiModelProperty(value = "创建时间")
	protected Date createTime;
	
	private String dataAccessPolicy;
	// 数据访问类型，1 只看自己的，2 看部门的，3 看公司的 4 自定义，参考policy字段
	@ApiModelProperty(value = "数据访问类型，1 只看自己的，2 看部门的，3 看公司的 4 自定义，参考policy字段")
	private Integer dataAccessType;
	@ApiModelProperty(value = "功能id")
	private String functionId;
	@ApiModelProperty(value = "对应角色id")
	private String roleId;
	
	public CoreRoleFunction() {
	}
	
}
