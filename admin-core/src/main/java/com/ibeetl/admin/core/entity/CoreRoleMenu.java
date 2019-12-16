package com.ibeetl.admin.core.entity;

import com.ibeetl.admin.core.util.ValidateConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.AssignID;

/*
 *
 * gen by beetlsql 2016-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "角色菜单关联")
public class CoreRoleMenu extends BaseEntity {
	
	@NotNull(message = "ID不能为空", groups = ValidateConfig.UPDATE.class)
	//@SeqID(name = ORACLE_CORE_SEQ_NAME)
	@AssignID
	protected String id;
	@ApiModelProperty(value = "菜单id")
	private String menuId;
	@ApiModelProperty(value = "角色id")
	private String roleId;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	public CoreRoleMenu() {
	}
	
	
}
