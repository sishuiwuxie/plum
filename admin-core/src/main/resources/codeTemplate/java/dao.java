package ${package};

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import ${basePackage}.entity.${entity.name};
import  ${basePackage}.entity.dto.${entity.name}DetailDTO;
/**
 * ${entity.displayName} Dao
 */
\@SqlResource("${entity.system}.${entity.code}")
public interface ${entity.name}Dao extends BaseMapper<${entity.name}>{
    PageQuery<${entity.name}> queryByCondition(PageQuery query);
    void batchDel${entity.name}ByIds( List<${entity.idAttribute.javaType}> ids);
    //关联查询版本，界面展现列表数据应当用本方法
    PageQuery<${entity.name}DetailDTO> queryDetailsByCondition(PageQuery<${entity.name}DetailDTO> query);

    ${entity.name}DetailDTO queryDetailById(String id);
}