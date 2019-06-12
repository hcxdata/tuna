package com.hobin.crm.model.dto;

import java.io.Serializable;

public class CityDictDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String cityCode;
	private String cityName;
	private String cityPostCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityPostCode() {
		return cityPostCode;
	}

	public void setCityPostCode(String cityPostCode) {
		this.cityPostCode = cityPostCode;
	}
}
