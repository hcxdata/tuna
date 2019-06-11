package com.hobin.crm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Entity represents a UserProfile.
 * 
 * @author xuxx
 */
@ApiModel
@Entity
@Table(name = "user_profiles")
public class UserProfile {
	@ApiModelProperty(notes = "The id of the user", example = "10001")
	@Id
	private Long uid;

	@ApiModelProperty(notes = "The real name of the user", example = "张三")
	@Column(name = "real_name", length = 32)
	private String realName;

	@ApiModelProperty(notes = "The nickname of user", example = "逍遥子")
	@Column(name = "nickname", length = 64)
	private String nickname;

	@ApiModelProperty(notes = "The gender of the user", example = "FEMALE")
	@Column(name = "gender")
	private Gender gender;

	@ApiModelProperty(notes = "The timestamp in epoch with the user's birthday", example = "1461201878")
	@Column(name = "birthday")
	private Date birthday;

	@ApiModelProperty(notes = "The email of the user", example = "xxx@abc.com")
	@Column(name = "email", length = 128)
	private String email;

	@ApiModelProperty(notes = "The office phone of the user", example = "010-8888888")
	@Column(name = "office_phone", length = 32)
	private String officePhone;

	@ApiModelProperty(notes = "The signature of the user", example = "哈哈哈哈")
	@Column(name = "signature", length = 128)
	private String signature;

	@ApiModelProperty(notes = "The department id of the user")
	@Column(name = "department_id")
	private Long departmentId;

	public UserProfile() {
	}

	public UserProfile(Long uid) {
		this.uid = uid;
		this.gender = Gender.UNKNOWN;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
