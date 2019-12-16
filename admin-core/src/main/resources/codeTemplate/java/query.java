package ${package};

import org.apache.commons.lang3.StringUtils;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.Tool;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

/**
 *${entity.comment}查询
 */
\@ApiModel(value = "${entity.comment}查询")
\@Data
\@EqualsAndHashCode(callSuper = true)
public class ${entity.name}Query extends PageParam {
    @for(attr in attrs) {
        @if(isNotEmpty(attr.dictType)) {
    \@Query(name = "${attr.displayName}", display = true,type=Query.TYPE_DICT,dict="${attr.dictType}")
    \@ApiModelProperty(value = "${attr.displayName}")
    private ${attr.javaType} ${attr.name};
        @} else if(attr.dateRange) {
    \@Query(name = "${attr.displayName}", display = true,type=Query.TYPE_DATE_BETWEEN)
    \@ApiModelProperty(value = "${attr.displayName}")
    private String ${attr.name};
    private Date ${strutil.replace (attr.name,"Range","")}Start;
    private Date ${strutil.replace (attr.name,"Range","")}End;
        @} else if(attr.dateTimeRange) {
    \@Query(name = "${attr.displayName}", display = true,type=Query.TYPE_DATETIME_BETWEEN)
    \@ApiModelProperty(value = "${attr.displayName}")
    private String ${attr.name};
    private Date ${strutil.replace (attr.name,"Range","")}Start;
    private Date ${strutil.replace (attr.name,"Range","")}End;
        @} else {
    \@Query(name = "${attr.displayName}", display = true)
    \@ApiModelProperty(value = "${attr.displayName}")
    private ${attr.javaType} ${attr.name};
        @}
    @}


 
}
