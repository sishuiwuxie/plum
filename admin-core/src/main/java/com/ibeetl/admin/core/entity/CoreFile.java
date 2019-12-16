package com.ibeetl.admin.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import com.ibeetl.admin.core.util.ValidateConfig;
import org.beetl.sql.core.TailBean;
import java.math.*;
import com.ibeetl.admin.core.entity.BaseEntity;

/* 
* 
* gen by Spring Boot2 Admin 2018-03-08
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "系统附件")
public class CoreFile extends BaseEntity {
    @NotNull(message = "ID不能为空", groups = ValidateConfig.UPDATE.class)
    //@SeqID(name = ORACLE_FILE_SEQ_NAME)
    @AssignID
    private String id;
    // 文件名称
    @ApiModelProperty(value = "文件名称")
    private String name;
    // 路径
    @ApiModelProperty(value = "路径")
    private String path;
    // 业务ID
    @ApiModelProperty(value = "业务ID")
    private String bizId;
    // 上传人id
    @ApiModelProperty(value = "上传人id")
    private String userId;
    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	@ApiModelProperty(value = "对应组织机构ID")
    private String orgId;
	@ApiModelProperty(value = "业务类型，区分附件的功能用")
	private String bizType;
	@ApiModelProperty(value = "附件对应的二级目录")
	private String fileBatchId;

    public CoreFile() {
    }

    
}
