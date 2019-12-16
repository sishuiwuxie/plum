package ${package};


import ${basePackage}.dao.${entity.name}Dao;
import ${basePackage}.entity.${entity.name};
import  ${basePackage}.entity.dto.${entity.name}DetailDTO;


import com.ibeetl.admin.core.service.CoreBaseService;
import com.ibeetl.admin.core.util.PlatformException;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;






/**
 * ${entity.displayName} Service
 */

\@Service
public class ${entity.name}Service extends CoreBaseService<${entity.name}>{

    \@Autowired
	private ${entity.name}Dao ${entity.code}Dao;

    public PageQuery<${entity.name}>queryByCondition(PageQuery query){
        PageQuery ret =  ${entity.code}Dao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }
		
	\@Transactional
	public void batchDel${entity.name}(List<${entity.idAttribute.javaType}> ids){
        try {
            ${entity.code}Dao.batchDel${entity.name}ByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除${entity.displayName}失败", e);
        }
    }
    
    //查询列表数据
    public PageQuery<${entity.name}DetailDTO>queryDetailsByCondition(PageQuery<${entity.name}DetailDTO> query){
        PageQuery ret =  ${entity.code}Dao.queryDetailsByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public ${entity.name}DetailDTO queryDetailById(String id){
        ${entity.name}DetailDTO ret=${entity.code}Dao.queryDetailById(id);
        return ret;
    }

}