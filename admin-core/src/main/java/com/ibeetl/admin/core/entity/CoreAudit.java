package com.ibeetl.admin.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.Map;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibeetl.admin.core.util.ValidateConfig;
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户访问记录审计")
public class CoreAudit extends BaseEntity  {
	
	//@SeqID(name = "ORACLE_AUDIT_SEQ_NAME")
	@AssignID
	@NotNull(message = "ID不能为空", groups = ValidateConfig.UPDATE.class)
	protected String id;

    //删除标识
    @JsonIgnore
    @ApiModelProperty(value = "删除标识")
    protected Integer delFlag;
    
    @ApiModelProperty(value = "访问状态,1=成功")
	private Integer success ;
	@ApiModelProperty(value = "类型代号")
	private String functionCode ;
	@ApiModelProperty(value = "类型名称")
	private String functionName ;
	@ApiModelProperty(value = "访问IP")
	private String ip ;
	@ApiModelProperty(value = "消息")
	private String message ;
	@ApiModelProperty(value = "用户名")
	private String userName ;
	@ApiModelProperty(value = "用户ID")
	private String userId ;
	@ApiModelProperty(value = "创建时间")
	protected Date createTime;
	
	public CoreAudit() {
	}

}