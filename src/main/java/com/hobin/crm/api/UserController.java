package com.hobin.crm.api;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hobin.crm.api.support.ErrorResource;
import com.hobin.crm.model.dto.ResetPasswordRequestDto;
import com.hobin.crm.model.dto.UserDto;
import com.hobin.crm.service.UserService;
import com.hobin.crm.service.exception.MissingPropertyException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "用户")
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService service;

	@ApiOperation(value = "Get the current user's detail.")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Not found user with username '%s'", response = ErrorResource.class) })
	@RequestMapping(value = "/self", method = RequestMethod.GET)
	public UserDto details(@ApiParam(hidden = true, access = "internal", value = "IGNORED") Principal principal) {
		return service.getDetails(principal.getName());
	}

	@ApiOperation(value = "Login")
	@ApiResponses(value = { @ApiResponse(code = 403, message = "Unauthorized"),
			@ApiResponse(code = 409, message = "User is online,try again") })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserDto login(@ApiParam(hidden = true, access = "internal", value = "IGNORED") Principal principal,
			HttpServletResponse response) {
		return service.login(principal.getName());
	}

	@ApiOperation(value = "Logout")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		session.invalidate();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}

	@ApiOperation("Reset the given user's password")
	@RequestMapping(value = "actions/reset-password", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void resetPassword(
			@ApiParam(required = true, value = "The request to reset password") @RequestBody ResetPasswordRequestDto request) {
		if (request == null || StringUtils.isEmpty(request.getPhone()) || StringUtils.isEmpty(request.getPassword())) {
			throw new MissingPropertyException(new String[] { "phone", "password" });
		}
		service.resetPassword(request);
	}

}
