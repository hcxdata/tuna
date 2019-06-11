package com.hobin.crm.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.hobin.crm.model.Gender;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Define the user detail profile information returned by the API service.
 * 
 * @author xuxx
 */
@ApiModel(value = "UserProfile")
public class UserProfileDto implements Serializable {
	private static final long serialVersionUID = 2664757089031349312L;

	public interface ProfileUpdateRequestView {
	}

	@ApiModelProperty(notes = "The user id of the user", example = "10001")
	@JsonView(ProfileUpdateRequestView.class)
	private Long uid;
	@ApiModelProperty(notes = "The username of the user", example = "user123")
	@JsonView(ProfileUpdateRequestView.class)
	private String username;
	@ApiModelProperty(notes = "The phone of the user")
	private String phone;
	@ApiModelProperty(notes = "The real name of the user", example = "tomas.zhang")
	@JsonView(ProfileUpdateRequestView.class)
	private String realName;
	@ApiModelProperty(notes = "The nickname of the user", example = "试验员")
	@JsonView(ProfileUpdateRequestView.class)
	private String nickname;
	@ApiModelProperty(notes = "The gender of the user", example = "MALE")
	@JsonView(ProfileUpdateRequestView.class)
	private Gender gender;
	@ApiModelProperty(notes = "The birthday of the consumer", dataType = "long", example = "1462451862")
	@JsonView(ProfileUpdateRequestView.class)
	private Date birthday;
	@ApiModelProperty(notes = "The link of the user avatar", example = "http://host/path/to/avatar.jpg")
	@JsonView(ProfileUpdateRequestView.class)
	private String avatar;
	@ApiModelProperty(notes = "The personalized signature", example = "中国智能制造2025！")
	@JsonView(ProfileUpdateRequestView.class)
	private String signature;
	@ApiModelProperty(notes = "The email of the user.")
	@JsonView(ProfileUpdateRequestView.class)
	private String email;
	@ApiModelProperty(notes = "The office phone of the user.")
	@JsonView(ProfileUpdateRequestView.class)
	private String officePhone;
	@ApiModelProperty(notes = "The registered time of the user.")
	@JsonView(ProfileUpdateRequestView.class)
	private Timestamp registeredAt;
	@ApiModelProperty(notes = "The department id of the user.")
	@JsonView(ProfileUpdateRequestView.class)
	private Long departmentId;

	public UserProfileDto() {
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public Timestamp getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Timestamp registeredAt) {
		this.registeredAt = registeredAt;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
}
