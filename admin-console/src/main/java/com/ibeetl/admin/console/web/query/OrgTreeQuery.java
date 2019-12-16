package com.ibeetl.admin.console.web.query;

import java.util.List;

import com.ibeetl.admin.core.web.query.PageParam;

/**
 * 描述: 带有组织树相关的查询
 */
public class OrgTreeQuery extends PageParam {
    protected String orgId; //组织id
    protected List<String> orgIds;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<String> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<String> orgIds) {
        this.orgIds = orgIds;
    }
}
