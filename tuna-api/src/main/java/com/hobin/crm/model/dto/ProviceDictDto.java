package com.hobin.crm.model.dto;

import java.io.Serializable;

public class ProviceDictDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String proviceCode;
	private String proviceName;
	private String postCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getProviceCode() {
		return proviceCode;
	}

	public void setProviceCode(String proviceCode) {
		this.proviceCode = proviceCode;
	}
	public String getProviceName() {
		return proviceName;
	}

	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
