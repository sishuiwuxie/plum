package com.ibeetl.admin.core.service;

import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.entity.CoreDict;
import com.ibeetl.admin.core.util.PlatformException;
import com.ibeetl.admin.core.util.enums.DelFlagEnum;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.TailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述:
 *
 * @author : xiandafu
 */
public class CoreBaseService<T> {
	
	@Autowired
	protected CoreDictService dictUtil;
	@Autowired
	@Qualifier("baseDataSourceSqlManagerFactoryBean")
	protected SQLManager sqlManager;
	
	/**
	 * 根据id查询对象，如果主键ID不存在
	 */
	public T queryById(Object id) {
		T t = sqlManager.single(getCurrentEntityClassz(), id);
		queryEntityAfter((Object) t);
		return t;
	}
	
	/**
	 * 根据id查询
	 *
	 * @param classz 返回的对象类型
	 * @param id 主键id
	 */
	public T queryById(Class<T> classz, Object id) {
		T t = sqlManager.unique(classz, id);
		queryEntityAfter((Object) t);
		return t;
	}
	
	/**
	 * 新增一条数据
	 *
	 * @param model 实体类
	 */
	@Transactional
	public boolean save(T model) {
		return sqlManager.insert(model, true) > 0;
	}
	
	
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 */
	@Transactional
	public boolean deleteById(List<String> ids) {
		if (ids == null || ids.isEmpty()) {
			throw new PlatformException("删除数据ID不能为空");
		}
		
		List<Object> list = new ArrayList<>();
		for (String id : ids) {
			Map map = new HashMap();
			// always id,delFlag for pojo
			map.put("id", id);
			map.put("delFlag", DelFlagEnum.DELETED.getValue());
			
			list.add(map);
		}
		int[] count = sqlManager.updateBatchTemplateById(getCurrentEntityClassz(), list);
		int successCount = 0;
		for (int successFlag : count) {
			successCount += successFlag;
		}
		return successCount == ids.size();
	}
	@Transactional
	public boolean deleteById(String id) {
		
		Map map = new HashMap();
		// always id,delFlag for pojo
		map.put("id", id);
		map.put("delFlag", DelFlagEnum.DELETED.getValue());
		int ret = sqlManager.updateTemplateById(getCurrentEntityClassz(), map);
		return ret == 1;
	}
	
	/**
	 * 根据id删除数据
	 *
	 * @param id 主键值
	 */
	@Transactional
	public int forceDelete(String id) {
		return sqlManager.deleteById(getCurrentEntityClassz(), id);
	}
	
	/**
	 * 根据id删除数据
	 *
	 * @param id 主键值
	 */
	@Transactional
	public int forceDelete(Class<T> classz, String id) {
		return sqlManager.deleteById(classz, id);
	}
	
	/**
	 * 更新，只更新不为空的字段
	 */
	@Transactional
	public boolean updateTemplate(T model) {
		return sqlManager.updateTemplateById(model) > 0;
	}
	
	/**
	 * 更新所有字段
	 */
	@Transactional
	public boolean update(T model) {
		return sqlManager.updateById(model) > 0;
	}
	
	
	/**
	 * 获取当前注入泛型T的类型
	 *
	 * @return 具体类型
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getCurrentEntityClassz() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	public void queryListAfter(List list) {
		for (Object bean : list) {
			queryEntityAfter(bean);
		}
	}
	
	public void queryEntityAfter(Object bean) {
		if (bean == null) {
			return;
		}
		
		if (!(bean instanceof TailBean)) {
			throw new PlatformException("指定的pojo" + bean.getClass() + " 不能获取数据字典，需要继承TailBean");
		}
		
		TailBean ext = (TailBean) bean;
		Class c = ext.getClass();
		do {
			Field[] fields = c.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Dict.class)) {
					field.setAccessible(true);
					Dict dict = field.getAnnotation(Dict.class);
					
					try {
						String display = "";
						Object fieldValue = field.get(ext);
						if (fieldValue != null) {
							CoreDict dbDict = dictUtil.findCoreDict(dict.type(), fieldValue.toString());
							display = dbDict != null ? dbDict.getName() : null;
						}
						ext.set(field.getName() + dict.suffix(), display);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
			c = c.getSuperclass();
		} while (c != TailBean.class);
		
	}
	
	
}
