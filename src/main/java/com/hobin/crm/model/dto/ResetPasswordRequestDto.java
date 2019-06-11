package com.hobin.crm.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Defines the request structure to reset forgotten password.
 * @author xuxx
 */
@ApiModel("ResetPasswordRequest")
public class ResetPasswordRequestDto implements Serializable {
    private static final long serialVersionUID = -346785015053104812L;

    @ApiModelProperty(required = true, example = "12345678901")
    private String phone;
    @ApiModelProperty(required = true)
    private String password;
    @ApiModelProperty(required = true, notes = "The check code received from your registered phone.")
    private String captcha;

    public ResetPasswordRequestDto() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
