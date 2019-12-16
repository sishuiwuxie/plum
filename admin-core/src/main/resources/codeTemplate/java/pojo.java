package ${package};

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;

import com.ibeetl.admin.core.util.ValidateConfig;

import org.beetl.sql.core.TailBean;
import java.math.*;

import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.entity.BaseEntity;

import org.beetl.sql.core.annotatoin.InsertIgnore;
import org.beetl.sql.core.annotatoin.Version;
import org.beetl.sql.core.annotatoin.LogicDelete;
import lombok.EqualsAndHashCode;
import lombok.Data;
import org.beetl.sql.core.annotatoin.AssignID;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* ${comment}
* gen by Spring Boot2 Admin ${date(),"yyyy-MM-dd"}
*/
\@Data
\@ApiModel(value = "${comment}")
\@EqualsAndHashCode(callSuper = false)
public class ${className} extends BaseEntity{

    @for(attr in attrs){
		@if(!isEmpty(attr.comment)){
    //${attr.comment}
		@}
		@if(attr.isId) {
    \@NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
    \@AssignID
		@}
		@if(isNotEmpty(attr.dictType)) {
    \@Dict(type="${attr.dictType}")
		@}
		@if(attr.name == "delFlag") {
	/*逻辑删除标志*/
	\@InsertIgnore
	\@LogicDelete(value = 1)
		@}
		@if(attr.name == "version") {
	/*乐观锁版本标志*/
	\@InsertIgnore
	\@Version
		@}
	\@ApiModelProperty(value = "${attr.comment}")
    private ${attr.type} ${attr.name} ;

	@}
    public ${className}(){
    }



}
