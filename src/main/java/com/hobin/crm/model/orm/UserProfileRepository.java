package com.hobin.crm.model.orm;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobin.crm.model.UserProfile;

/**
 * @author sam
 */
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByUid(Long uid);
}
