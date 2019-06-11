package com.hobin.crm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.hobin.crm.model.User;
import com.hobin.crm.model.UserProfile;
import com.hobin.crm.model.dto.ResetPasswordRequestDto;
import com.hobin.crm.model.dto.UserDto;
import com.hobin.crm.model.dto.UserProfileDto;
import com.hobin.crm.model.orm.UserProfileRepository;
import com.hobin.crm.model.orm.UserRepository;
import com.hobin.crm.service.EncryptService;
import com.hobin.crm.service.UserService;
import com.hobin.crm.service.exception.ErrorCode;
import com.hobin.crm.service.exception.ResourceNotFoundException;
import com.hobin.crm.service.exception.ServiceException;

/**
 * The default implementation for the user management service interface.
 * 
 * @author xuxx
 */
@Service
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{11}");

	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EncryptService encryptService;
	@Autowired
	private UserProfileRepository profileRepository;

	@Override
	public UserDto login(String username) {
		UserDto user = getDetails(username);
		if (user != null) {
			logger.info("Login with username '{}'", user.getUsername());
			return user;
		}
		throw new ServiceException(String.format("User '%s' is no found"), ErrorCode.NOT_FOUND);
	}

	@Override
	public void resetPassword(ResetPasswordRequestDto request) {
		User found = repository.findOneByPhone(encryptService.encrypt(request.getPhone()));
		if (found == null) {
			throw new ResourceNotFoundException("user", ErrorCode.NOT_FOUND);
		}

		found.setPassword(passwordEncoder.encode(request.getPassword()));
		String encodedMqttPw = passwordEncoder.encode(generateMqttPassword(found.getUsername(), request.getPassword()));
		found.setSalt(extractSalt(encodedMqttPw));
		found.setNoSaltPassword(encodedMqttPw.substring(found.getSalt().length()));
		repository.save(found);
		logger.info(String.format("Reset the password of user with phone '%s'", request.getPhone()));
	}

	private String generateMqttPassword(String username, String password) {
		// We use base64(username:password) as the mqtt password
		return Base64Utils.encodeToString(String.format("%s:%s", username, password).getBytes());
	}

	private String extractSalt(String encodedPassword) {
		int keyLengthInHexString = KeyGenerators.secureRandom().getKeyLength() * 2;
		int lastIndex = keyLengthInHexString > encodedPassword.length() ? encodedPassword.length()
				: keyLengthInHexString;
		return encodedPassword.substring(0, lastIndex);
	}

	@Override
	public UserDto getDetails(String username) {
		User found = repository.findOneByUsername(username);
		if (found == null) {
			logger.warn("Not found user with username '{}'", username);
			throw new ResourceNotFoundException("user", ErrorCode.NOT_FOUND);
		}
		return toUserDto(found);
	}

	private UserDto toUserDto(User user) {
		UserDto result = new UserDto();
		UserProfileDto profile = toUserProfileDto(user);
		if (user != null) {
			result.setId(user.getId());
			result.setUsername(user.getUsername());
			String phone = user.getPhone();
			if (!isRawPhone(phone)) {
				try {
					phone = encryptService.decrypt(phone);
				} catch (Exception e) {
					logger.error(String.format("Decrypt phone '%s' failed", phone), e);
				}
			}
			result.setPhone(phone);
			result.setType(user.getType());
			result.setStatus(user.getStatus());
			result.setAvatar(user.getAvatar());
			result.setRegisteredAt(user.getRegisteredAt());
			result.setImToken(user.getImToken());
			result.setProfile(profile);

			List<String> roles = new ArrayList<String>();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				roles.add(ga.getAuthority());
			}
			result.setRoles(roles);
		}
		return result;
	}

	private UserProfileDto toUserProfileDto(User user) {
		UserProfileDto profileDto = new UserProfileDto();
		if (user == null) {
			return profileDto;
		}

		BeanUtils.copyProperties(user, profileDto);
		String phone = user.getPhone();
		if (!isRawPhone(phone)) {
			try {
				phone = encryptService.decrypt(phone);
			} catch (Exception e) {
				logger.error(String.format("Decrypt phone '%s' failed", phone), e);
			}
		}
		profileDto.setPhone(phone);
		UserProfile profile = profileRepository.findById(user.getId()).get();
		if (profile != null) {
			BeanUtils.copyProperties(profile, profileDto);
		}
		return profileDto;
	}

	private boolean isRawPhone(String phone) {
		return PHONE_PATTERN.matcher(phone).matches();
	}
}
