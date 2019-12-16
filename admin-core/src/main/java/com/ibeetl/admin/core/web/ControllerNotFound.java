package com.ibeetl.admin.core.web;

import org.beetl.ext.simulate.WebSimulate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 没有找到匹配的Controller
 *
 * @author xiandafu
 */
@Controller
public class ControllerNotFound {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	WebSimulate webSimulate;

	@RequestMapping("/**/*.do")
	public void error(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		log.info("没有配置 url " + request.getRequestURI() + ",确认所访问Controller是否存在，是否被Spring Boot管理");
		throw new RuntimeException("未找到Controller类处理此请求 " + request.getRequestURI());
	}

}
