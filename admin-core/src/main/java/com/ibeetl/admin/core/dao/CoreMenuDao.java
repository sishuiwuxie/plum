package com.ibeetl.admin.core.dao;

import com.ibeetl.admin.core.entity.CoreMenu;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("core.coreMenu")
public interface CoreMenuDao extends BaseMapper<CoreMenu> {

	public void queryByCondition(PageQuery query);

	public void clearMenuFunction(List<String> functionIds);

	public List<CoreMenu> allMenuWithURL();

}
