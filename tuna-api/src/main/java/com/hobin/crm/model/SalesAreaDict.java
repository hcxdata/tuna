package com.hobin.crm.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales_area_dict")
public class SalesAreaDict implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable=false )
	private Integer id;
	@Column(name = "sales_area_code", nullable=false )
	private String salesAreaCode;
	@Column(name = "sales_area_name", nullable=false )
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
