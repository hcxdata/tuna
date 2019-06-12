package com.hobin.crm.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class CustomerInfoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer custId;
	private String custName;
	private String custAlias;
	private String salesAreaCode;
	private String custProviceCode;
	private String custCityCode;
	private String custAddress;
	private Integer custGrade;
	private String custWebsite;
	private Integer custEnable;
	private Date initalCreateDate;
	private Integer initalCreatorId;
	private String description;

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAlias() {
		return custAlias;
	}

	public void setCustAlias(String custAlias) {
		this.custAlias = custAlias;
	}
	public String getSalesAreaCode() {
		return salesAreaCode;
	}

	public void setSalesAreaCode(String salesAreaCode) {
		this.salesAreaCode = salesAreaCode;
	}
	public String getCustProviceCode() {
		return custProviceCode;
	}

	public void setCustProviceCode(String custProviceCode) {
		this.custProviceCode = custProviceCode;
	}
	public String getCustCityCode() {
		return custCityCode;
	}

	public void setCustCityCode(String custCityCode) {
		this.custCityCode = custCityCode;
	}
	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public Integer getCustGrade() {
		return custGrade;
	}

	public void setCustGrade(Integer custGrade) {
		this.custGrade = custGrade;
	}
	public String getCustWebsite() {
		return custWebsite;
	}

	public void setCustWebsite(String custWebsite) {
		this.custWebsite = custWebsite;
	}
	public Integer getCustEnable() {
		return custEnable;
	}

	public void setCustEnable(Integer custEnable) {
		this.custEnable = custEnable;
	}
	public Date getInitalCreateDate() {
		return initalCreateDate;
	}

	public void setInitalCreateDate(Date initalCreateDate) {
		this.initalCreateDate = initalCreateDate;
	}
	public Integer getInitalCreatorId() {
		return initalCreatorId;
	}

	public void setInitalCreatorId(Integer initalCreatorId) {
		this.initalCreatorId = initalCreatorId;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
