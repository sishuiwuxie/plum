package com.ibeetl.admin.core.file;

import lombok.Data;

import java.io.OutputStream;


public abstract class FileItem {
	protected String id;
	protected String name;
	protected String path;
	boolean isTemp = false;

	public abstract OutputStream openOutputStream();

	public abstract void copy(OutputStream os);

	public abstract boolean delete();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isTemp() {
		return isTemp;
	}

	public void setTemp(boolean temp) {
		isTemp = temp;
	}
}
