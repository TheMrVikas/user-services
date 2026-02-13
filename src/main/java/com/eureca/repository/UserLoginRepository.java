package com.eureca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eureca.entity.UserLoginEntity;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Long>{
	/**
	 * 
	 * @param userName
	 * @return
	 */
	Optional<UserLoginEntity> findByUserName(String userName);
}
