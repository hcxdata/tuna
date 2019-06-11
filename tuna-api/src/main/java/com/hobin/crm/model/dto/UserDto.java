package com.hobin.crm.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.hobin.crm.model.UserStatus;
import com.hobin.crm.model.UserType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Defines the user structure returned by the api service.
 * @author xuxx
 */
@ApiModel("User")
public class UserDto implements Serializable {
    private static final long serialVersionUID = 6287856792355707893L;

    @ApiModelProperty(notes = "The user identifier", example = "10001")
    private Long id;
    @ApiModelProperty(notes = "The unique user name", example = "tomas")
    private String username;
    @ApiModelProperty
    private String phone;
    @ApiModelProperty(dataType = "UserType")
    private UserType type;
    @ApiModelProperty(dataType = "UserStatus")
    private UserStatus status;
    @ApiModelProperty(notes = "The timestamp in epoch when the user registered.", example = "1458104760000")
    private Timestamp registeredAt;
    @ApiModelProperty(notes = "The link of the user's avatar.", example = "http://host/path/to/avatar.jpg")
    private String avatar;
    @ApiModelProperty(notes = "The token used to send instant messages .")
    private String imToken;
    @ApiModelProperty(notes = "The user roles")
    private List<String> roles;
    @ApiModelProperty(notes = "The object of userProfile")
    private UserProfileDto profile;

    public UserDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserProfileDto getProfile() {
        return profile;
    }

    public void setProfile(UserProfileDto profile) {
        this.profile = profile;
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
