package com.ibeetl.admin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.util.ValidateConfig;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.AssignID;

/*
 *   用户实体
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户")
public class CoreUser extends BaseEntity {
	
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
	
	// 登录名，编号
	@NotBlank(message = "用户编号不能为空", groups = ValidateConfig.ADD.class)
	@Null(message = "用户编号不能为空", groups = ValidateConfig.UPDATE.class)
	@ApiModelProperty(value = " 登录名，编号")
	private String code;
	
	// 用户姓名
	@NotBlank(message = "用户名不能为空")
	@ApiModelProperty(value = "用户姓名")
	private String name;
	
	// 组织机构id
	@ApiModelProperty(value = "组织机构id")
	private String orgId;
	
	// 密码
	@JsonIgnore
	@ApiModelProperty(value = "密码")
	private String password;
	
	@Dict(type = CoreDictType.USER_STATE)
	@ApiModelProperty(value = "状态,字典")
	private String state;
	
	//扩展例子
	@Dict(type = "job_type")
	@ApiModelProperty(value = "工作类型0，字典")
	private String jobType0;
	
	@Dict(type = "job_type")
	@ApiModelProperty(value = "工作类型1，字典")
	private String jobType1;
	
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	
	/*用户的个人资料附件，保存到Core_File 表里*/
	@ApiModelProperty(value = "用户的个人资料附件")
	private String attachmentId;
	
}
