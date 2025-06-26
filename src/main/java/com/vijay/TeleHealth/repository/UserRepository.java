package com.vijay.TeleHealth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vijay.TeleHealth.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByName(String name);
	
	Optional<User> findById(Long id);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByPhoneNumber(String phoneNumber);
	
	@Query("select count(u) > 0 from User u where u.email=:email")
	boolean exsistsByEmail(@Param("email") String email);
	
	@Query("select count(u)>0 from User u where u.phoneNumber = :phoneNumber")
	boolean exsistsByPhoneNumber(@Param("phoneNumber")String phoneNumber);
	
	
}
