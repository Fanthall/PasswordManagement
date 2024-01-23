package com.fanthal.PasswordManagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fanthal.PasswordManagement.model.Password;

public interface PasswordsRepository extends JpaRepository<Password, Long> {

	@Query("SELECT p FROM Password p WHERE p.username=:username")
	public Password findByUsername(String username);

	@Query("SELECT case when count(p)>0 then true else false end FROM Password p WHERE p.username=:username AND p.user.id=:userId")
	public boolean existByUsernameAndUserId(String username, Long userId);

	@Query("SELECT p FROM Password p WHERE "
			+ " LOWER(p.name) LIKE LOWER(CONCAT('%',:searchParam,'%')) OR "
			+ " LOWER(p.username) LIKE LOWER(CONCAT('%',:searchParam,'%'))")
	public Page<Password> searchPasswordsWithuserId(String searchParam, Pageable paging);
}
