package com.ibeetl.admin.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.util.ValidateConfig;
import com.ibeetl.admin.core.util.enums.CoreDictType;


/**
 * 机构对象，
 * 有母公司，一个系统只有一个母公司，多个集团，集团下可以有多个公司，子公司，部门。如果系统不符合这个设定，需要修改·
 * 
 * <br/>
 * 映射了上级机构，可以通过org.parentOrg.xxx取上级机构的属性
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "机构对象，一个树，唯一根节点")
public class CoreOrg extends BaseEntity {
    
    // 自增id
	@NotNull(message = "ID不能为空", groups = ValidateConfig.UPDATE.class)
	//@SeqID(name = ORACLE_CORE_SEQ_NAME)
	@AssignID
    private String id;

    //删除标识
    @JsonIgnore
    @ApiModelProperty(value = "删除标识")
    protected Integer delFlag= 0;
    
    //创建时间
    @ApiModelProperty(value = "创建时间")
    protected Date createTime;

    // 机构编号
    @NotBlank(message = "组织编号不能为空", groups = ValidateConfig.ADD.class)
    @ApiModelProperty(value = "机构编号")
    private String code;

    // 机构名称
    @NotBlank(message = "组织名称不能为空", groups = {ValidateConfig.ADD.class, ValidateConfig.UPDATE.class})
    @ApiModelProperty(value = "机构名称")
    private String name;

    // 上层机构id
    @ApiModelProperty(value = "上层机构id")
    private String parentOrgId;

    // 机构类型 1 集团 2 公司，3 部门，4 小组
    @Dict(type = CoreDictType.ORG_TYPE)
    @NotBlank(message = "组织类型不能为空", groups = ValidateConfig.class)
    @ApiModelProperty(value = "机构类型 1 集团 2 公司，3 部门，4 小组")
    private String type;


   
}
