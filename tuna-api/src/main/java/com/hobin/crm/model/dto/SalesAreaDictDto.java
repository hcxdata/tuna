package com.hobin.crm.model.dto;

import java.io.Serializable;

public class SalesAreaDictDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String salesAreaCode;
	private String salesAreaName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getSalesAreaCode() {
		return salesAreaCode;
	}

	public void setSalesAreaCode(String salesAreaCode) {
		this.salesAreaCode = salesAreaCode;
	}
	public String getSalesAreaName() {
		return salesAreaName;
	}

	public void setSalesAreaName(String salesAreaName) {
		this.salesAreaName = salesAreaName;
	}
}
