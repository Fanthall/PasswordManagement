package com.fanthal.PasswordManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fanthal.PasswordManagement.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	@Query("SELECT rt FROM RefreshToken rt where rt.token=:token")
	RefreshToken findByToken(String token);

	@Query("SELECT rt FROM RefreshToken rt where rt.user.id=:userId")
	RefreshToken findByUserId(Long userId);

	@Query("SELECT case when count(rt)>0 then true else false end  FROM RefreshToken rt where rt.user.id=:userId")
	boolean existByUserId(Long userId);

}
