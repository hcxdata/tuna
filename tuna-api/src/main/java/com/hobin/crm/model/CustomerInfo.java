package com.hobin.crm.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "customer_info")
public class CustomerInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id", nullable=false )
	private Integer custId;
	@Column(name = "cust_name", nullable=false )
	private String custName;
	@Column(name = "cust_alias", nullable=false )
	private String custAlias;
	@Column(name = "sales_area_code", nullable=true )
	private String salesAreaCode;
	@Column(name = "cust_provice_code", nullable=true )
	private String custProviceCode;
	@Column(name = "cust_city_code", nullable=true )
	private String custCityCode;
	@Column(name = "cust_address", nullable=true )
	private String custAddress;
	@Column(name = "cust_grade", nullable=true )
	private Integer custGrade;
	@Column(name = "cust_website", nullable=true )
	private String custWebsite;
	@Column(name = "cust_enable", nullable=true )
	private Integer custEnable;
	@Column(name = "inital_create_date", nullable=false )
	private Date initalCreateDate;
	@Column(name = "inital_creator_id", nullable=false )
	private Integer initalCreatorId;
	@Column(name = "description", nullable=true )
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
