package com.ibeetl.admin.core.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.UpdateIgnore;

import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.util.ValidateConfig;
import com.ibeetl.admin.core.util.enums.CoreDictType;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "功能点")
public class CoreFunction extends BaseEntity   {
	
	@NotNull(message = "ID不能为空", groups = ValidateConfig.UPDATE.class)
	//@SeqID(name = ORACLE_CORE_SEQ_NAME)
	@AssignID
    protected String id;

    //创建时间
	@UpdateIgnore
	@ApiModelProperty("创建时间")
    protected Date createTime;
	
	@ApiModelProperty("访问地址")
	private String accessUrl ;
	
	@NotBlank
	@ApiModelProperty("功能代码")
	private String code ;
 
	@NotBlank
    @ApiModelProperty("功能名称")
	private String name ;
	
    @NotBlank
	@ApiModelProperty("上一级功能ID")
	private String parentId ;
	
	@Dict(type=CoreDictType.FUNCTION_TYPE)
	@NotBlank
	@ApiModelProperty("功能类型")
	private String type = null;//"FN0" ;
	

	public boolean hasParent(){
		return StringUtils.isNoneBlank(parentId);
	}

}
