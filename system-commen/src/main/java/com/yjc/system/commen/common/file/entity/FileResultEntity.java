package com.yjc.system.commen.common.file.entity;

import java.io.File;

public class FileResultEntity {

    private String code;

    private String message;

    private File file;
    
    public FileResultEntity(){}
    
	public FileResultEntity(String code, String message, File file) {
		super();
		this.code = code;
		this.message = message;
		this.file = file;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
    
}