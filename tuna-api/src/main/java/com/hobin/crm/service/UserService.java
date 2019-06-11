package com.hobin.crm.service;

import com.hobin.crm.model.dto.ResetPasswordRequestDto;
import com.hobin.crm.model.dto.UserDto;

public interface UserService {

	UserDto login(String username);

	UserDto getDetails(String username);

	void resetPassword(ResetPasswordRequestDto request);

}
