package com.ibeetl.admin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.ibeetl.admin.core.util.ValidateConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.AssignID;

/**
 * 描述: 字典
 *
 * @author : xiandafu
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "系统字典")
public class CoreDict extends BaseEntity {
	
	@NotNull(message = "ID不能为空", groups = ValidateConfig.UPDATE.class)
	//@SeqID(name = ORACLE_CORE_SEQ_NAME)
	@AssignID
	private String id;

	@ApiModelProperty(value = "数据值")
	private String value;   // 数据值

	//删除标识
	@JsonIgnore
	@ApiModelProperty(value = "删除标识")
	protected Integer delFlag = 0;

	//创建时间
	protected Date createTime;

	@NotBlank(message = "字典类型不能为空", groups = ValidateConfig.ADD.class)
	@JsonView(TypeListView.class)
	@ApiModelProperty(value = "类型")
	private String type;    //类型

	@JsonView(TypeListView.class)
	@NotBlank(message = "字典类型描述不能为空")
	@ApiModelProperty(value = "类型描述")
	private String typeName; //类型描述
	@NotBlank(message = "字典值不能为空", groups = ValidateConfig.ADD.class)
	
	@NotBlank(message = "字典值名称不能为空")
	@ApiModelProperty(value = "标签名")
	private String name;    // 标签名
	
	@ApiModelProperty(value = "排序")
	private Integer sort;    // 排序
	
	@ApiModelProperty(value = "父Id")
	private String parent;  //父Id
	
	@ApiModelProperty(value = "备注")
	private String remark;  //备注
	
	
	public interface TypeListView {
	
	}
	
	
 
	
}
