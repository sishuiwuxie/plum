package com.ibeetl.admin.core.file;

import java.io.OutputStream;

public abstract class PersistFileItem extends FileItem {
    protected String id;
    protected String userId;
    protected String orgId;
    protected String bizType;
    protected String bizId;
    FileTag[] tags;
    public PersistFileItem() {
        this.isTemp = false;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public FileTag[] getTags() {
        return tags;
    }

    public void setTags(FileTag[] tags) {
        this.tags = tags;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
