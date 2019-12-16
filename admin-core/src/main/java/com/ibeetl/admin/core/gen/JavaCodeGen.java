package com.ibeetl.admin.core.gen;

import com.ibeetl.admin.core.gen.model.Attribute;
import com.ibeetl.admin.core.gen.model.Entity;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaCodeGen implements AutoGen {
	static String CR = System.getProperty("line.separator");
	String basePackage;
	Entity entity;
	String basicFunctionCode = null;

	public JavaCodeGen(String basePackage, Entity entity, String basicFunctionCode) {
		this.basePackage = basePackage;
		this.entity = entity;
		this.basicFunctionCode = basicFunctionCode;
	}

	@Override
	public void make(Target target, Entity entity) {

		JavaEntityGen entityGen = new JavaEntityGen(this);
		entityGen.make(target, entity);

		JavaEntityDTOGen entityDTOGen = new JavaEntityDTOGen(this);
		entityDTOGen.make(target, entity);

		JavaDaoGen daoGen = new JavaDaoGen(this);
		daoGen.make(target, entity);

		JavaServiceGen serviceGen = new JavaServiceGen(this);
		serviceGen.make(target, entity);

		JavaControllerGen webGen = new JavaControllerGen(this);
		webGen.make(target, entity);

		JavaQueryGen queryGen = new JavaQueryGen(this);
		queryGen.make(target, entity);

	}

	@Override
	public String getName() {
		return "";
	}

}

class JavaEntityGen implements AutoGen {
	JavaCodeGen gen;

	public JavaEntityGen(JavaCodeGen gen) {
		this.gen = gen;

	}

	@Override
	public void make(Target target, Entity entity) {
		GroupTemplate gt = target.getGroupTemplate();
		Template template = gt.getTemplate("/java/pojo.java");
		template.binding("entity", entity);
		template.binding("target", target);
		template.binding("package", gen.basePackage + ".entity");
		template.binding("className", entity.getName());
		List<Map<String, Object>> attrs = new ArrayList<Map<String, Object>>();
		for (Attribute attr : entity.getList()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("comment", attr.getComment());
			map.put("type", attr.getJavaType());
			map.put("name", attr.getName());
			map.put("methodName", BaseTarget.upperFirst(attr.getName()));
			map.put("isId", attr.isId());
			map.put("dictType", attr.getDictType());
			attrs.add(map);

		}
		template.binding("attrs", attrs);
		String srcHead = "";
		srcHead += "import java.math.*;" + JavaCodeGen.CR;
		srcHead += "import java.util.Date;" + JavaCodeGen.CR;

		srcHead += "import java.sql.Timestamp;" + JavaCodeGen.CR;
		template.binding("imports", srcHead);
		template.binding("comment", entity.getComment());
		String content = template.render();
		target.flush(this, content);
	}

	@Override
	public String getName() {
		return gen.entity.getName() + ".java";
	}

}

class JavaEntityDTOGen implements AutoGen {
	JavaCodeGen gen;

	public JavaEntityDTOGen(JavaCodeGen gen) {
		this.gen = gen;

	}

	@Override
	public void make(Target target, Entity entity) {
		GroupTemplate gt = target.getGroupTemplate();
		Template template = gt.getTemplate("/java/dto.java");
		template.binding("entity", entity);
		template.binding("target", target);
		template.binding("entityPackage", gen.basePackage + ".entity");
		template.binding("package", gen.basePackage + ".entity.dto");
		template.binding("className", entity.getName() + "DetailDTO");
		template.binding("entityClassName", entity.getName());
		List<Map<String, Object>> attrs = new ArrayList<Map<String, Object>>();
		for (Attribute attr : entity.getList()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("comment", attr.getComment());
			map.put("type", attr.getJavaType());
			map.put("name", attr.getName());
			map.put("methodName", BaseTarget.upperFirst(attr.getName()));
			map.put("isId", attr.isId());
			map.put("dictType", attr.getDictType());
			attrs.add(map);

		}
		template.binding("attrs", attrs);
		String srcHead = "";
		srcHead += "import java.math.*;" + JavaCodeGen.CR;
		srcHead += "import java.util.Date;" + JavaCodeGen.CR;

		srcHead += "import java.sql.Timestamp;" + JavaCodeGen.CR;
		template.binding("imports", srcHead);
		template.binding("comment", entity.getComment());
		String content = template.render();
		target.flush(this, content);
	}

	@Override
	public String getName() {
		return gen.entity.getName() + "DetailDTO.java";
	}

}

class JavaDaoGen implements AutoGen {
	JavaCodeGen gen;

	public JavaDaoGen(JavaCodeGen gen) {
		this.gen = gen;
	}

	@Override
	public void make(Target target, Entity entity) {
		GroupTemplate gt = target.getGroupTemplate();
		Template template = gt.getTemplate("/java/dao.java");
		template.binding("entity", entity);
		template.binding("target", target);
		template.binding("package", gen.basePackage + ".dao");
		template.binding("basePackage", gen.basePackage);
		String content = template.render();
		target.flush(this, content);
	}

	@Override
	public String getName() {
		return gen.entity.getName() + "Dao.java";
	}

}

class JavaServiceGen implements AutoGen {
	JavaCodeGen gen;

	public JavaServiceGen(JavaCodeGen gen) {
		this.gen = gen;
	}

	@Override
	public void make(Target target, Entity entity) {
		GroupTemplate gt = target.getGroupTemplate();
		Template template = gt.getTemplate("/java/service.java");
		template.binding("entity", entity);
		template.binding("target", target);
		template.binding("package", gen.basePackage + ".service");
		template.binding("basePackage", gen.basePackage);
		String content = template.render();
		target.flush(this, content);
	}

	@Override
	public String getName() {
		return gen.entity.getName() + "Service.java";
	}

}

class JavaControllerGen implements AutoGen {
	JavaCodeGen gen;

	public JavaControllerGen(JavaCodeGen gen) {
		this.gen = gen;
	}

	@Override
	public void make(Target target, Entity entity) {
		GroupTemplate gt = target.getGroupTemplate();
		Template template = gt.getTemplate("/java/controller.java");
		template.binding("entity", entity);
		template.binding("target", target);
		template.binding("package", gen.basePackage + ".web");
		template.binding("basePackage", gen.basePackage);
		template.binding("basicfunctionCode", gen.basicFunctionCode);
		//主键的方法名 如ProjectId
		template.binding("idMethodName", BaseTarget.upperFirst(entity.getIdAttribute().getName()));
		template.binding("createUserIdFieldExists", entity.colExists("create_user_id"));
		template.binding("createTimeFieldExists", entity.colExists("create_time"));
		template.binding("lastUpdateTimeFieldExists", entity.colExists("last_update_time"));

		String content = template.render();
		target.flush(this, content);
	}

	@Override
	public String getName() {
		return gen.entity.getName() + "Controller.java";
	}

}

class JavaQueryGen implements AutoGen {
	JavaCodeGen gen;

	public JavaQueryGen(JavaCodeGen gen) {
		this.gen = gen;
	}

	@Override
	public void make(Target target, Entity entity) {
		GroupTemplate gt = target.getGroupTemplate();
		Template template = gt.getTemplate("/java/query.java");
		List<Attribute> list = new ArrayList<Attribute>();
		for (Attribute attr : entity.getList()) {
			if (attr.isShowInQuery()) {
				list.add(attr);
			}
		}

		if (list.isEmpty()) {
			list.add(entity.getIdAttribute());
		}

		template.binding("entity", entity);
		template.binding("target", target);
		template.binding("package", gen.basePackage + ".web.query");
		template.binding("basePackage", gen.basePackage);
		template.binding("attrs", list);
		String content = template.render();
		target.flush(this, content);
	}

	@Override
	public String getName() {
		return gen.entity.getName() + "Query.java";
	}

}





