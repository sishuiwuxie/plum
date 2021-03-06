package com.ibeetl.admin.core.util;

import com.ibeetl.admin.core.entity.CoreOrg;
import com.ibeetl.admin.core.rbac.tree.OrgItem;

import java.util.ArrayList;
import java.util.List;

public class OrgBuildUtil {
	private OrgBuildUtil() {

	}

	public static void buildTreeNode(OrgItem parent, List<CoreOrg> list) {

		String id = parent.getId();
		List<CoreOrg> dels = new ArrayList<>();
		for (CoreOrg sysOrg : list) {
			if (sysOrg.getParentOrgId() != null && sysOrg.getParentOrgId().equals(id)) {
				OrgItem item = new OrgItem(sysOrg);
				item.setParent(parent);
				dels.add(sysOrg);
			}
		}
		list.removeAll(dels);

		if (list.isEmpty()) {
			return;
		}
		for (OrgItem child : parent.getChildren()) {
			buildTreeNode(child, list);
		}

	}
}
