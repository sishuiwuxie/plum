package com.ibeetl.admin.core.rbac;

import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * 数据权限接口类
 * @author Administrator
 *
 */
public interface DataAccess {
	 DataAccessResullt getOrg(String userId,String orgId );
	 String getName();
	 Integer getType();
}
