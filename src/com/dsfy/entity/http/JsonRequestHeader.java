package com.dsfy.entity.http;

/**
 * 请求头
 * 目前没什么用
 * @author 斌
 *
 */
public class JsonRequestHeader {
	private String className;

	private String method;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
