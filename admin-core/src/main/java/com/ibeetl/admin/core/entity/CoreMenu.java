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
import org.beetl.sql.core.annotatoin.UpdateIgnore;

import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.util.ValidateConfig;
import com.ibeetl.admin.core.util.enums.CoreDictType;

/**
 * 系统菜单
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "系统菜单")
public class CoreMenu extends BaseEntity {

    public static final String TYPE_SYSTEM = "MENU_S";
    public static final String TYPE_NAV = "MENU_N";
    public static final String TYPE_MENUITEM = "MENU_M";

	@NotNull(message = "ID不能为空", groups = ValidateConfig.UPDATE.class)
	//@SeqID(name = ORACLE_CORE_SEQ_NAME)
    @AssignID
    protected String id;

    //创建时间
	@UpdateIgnore
	@ApiModelProperty(value = "创建时间")
    protected Date createTime;

    //菜单代码
    @NotBlank(message = "菜单代码不能为空", groups = ValidateConfig.ADD.class)
    @ApiModelProperty(value = "菜单代码")
    private String code;

    //功能id
    @ApiModelProperty(value = "功能id")
    private String functionId;

    //类型  /*1 系统 2 导航 3 菜单项(与功能点有关)*/
    @NotNull(message = "菜单类型不能为空")
    @Dict(type = CoreDictType.MENU_TYPE)
    @ApiModelProperty(value = "类型   1 系统 2 导航 3 菜单项(与功能点有关)")
    private String type;

    //菜单名称
    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty(value = "菜单名称")
    private String name;

    //上层菜单id
    @NotNull(message = "上层菜单不能为空")
    @ApiModelProperty(value = "上层菜单id")
    private String parentMenuId;

    //排序
    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "排序")
    private Integer seq;

    //图标
    @ApiModelProperty(value = "图标")
    private String icon;

    public CoreMenu() {
    }

    
}
