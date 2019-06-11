package com.hobin.crm.model.orm;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobin.crm.model.User;

/**
 * @author xuxx
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUsername(String username);

	User findOneByPhone(String phone);

	User findOneByPhoneAndUsername(String phone, String username);
}
