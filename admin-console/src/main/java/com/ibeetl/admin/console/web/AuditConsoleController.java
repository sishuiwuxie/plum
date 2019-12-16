package com.ibeetl.admin.console.web;

import com.ibeetl.admin.console.service.AuditConsoleService;
import com.ibeetl.admin.console.web.query.AuditQuery;
import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.entity.CoreAudit;
import com.ibeetl.admin.core.util.AnnotationUtil;
import com.ibeetl.admin.core.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 用户访问审计接口
 *
 * @author xiandafu
 */
@Controller
@Api(value = "用户管理接口")
public class AuditConsoleController {
	private static final String MODEL = "/admin/audit";

	@Autowired
	AuditConsoleService auditConsoleService;


	/*页面*/

	@GetMapping(MODEL + "/index.do")
	@Function("trace")
	@ApiOperation(value = "页面")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/admin/audit/index.html");
		view.addObject("search", AuditQuery.class.getName());
		return view;
	}


	/*Json*/

	@PostMapping(MODEL + "/view.json")
	@ResponseBody
	@Function("trace")
	@ApiOperation(value = "JSON内容")
	public JsonResult<CoreAudit> view(String id) {
		CoreAudit audit = auditConsoleService.queryById(id);
		return JsonResult.success(audit);
	}

	@RequestMapping(MODEL + "/list.json")
	@Function("trace")
	@ResponseBody
	public JsonResult<PageQuery<CoreAudit>> list(AuditQuery condition) {

		PageQuery<CoreAudit> page = condition.getPageQuery();
		auditConsoleService.queryByCondition(page);
		return JsonResult.success(page);
	}

	@PostMapping(MODEL + "/list/condition.json")
	@Function("trace")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> listCondition() {
		List<Map<String, Object>> list = AnnotationUtil.getInstance().getAnnotations(Query.class, AuditQuery.class);
		return JsonResult.success(list);
	}

}
