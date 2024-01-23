package com.fanthal.PasswordManagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fanthal.PasswordManagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE LOWER(u.username)=LOWER(:username)")
	public User findByUsername(String username);

	@Query("Select case when count(u)>0 then true else false end from User u where LOWER(u.username)=LOWER(:username)")
	public boolean checkUserExistwithUsername(String username);

	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	public User findUserWithUsernameAndPassword(String username, String password);

	@Override
	@Query("SELECT u FROM User u WHERE u.id=:id")
	public User getById(Long id);

	@Query("SELECT u FROM User u WHERE u.active IS true AND"
			+ "(LOWER(u.username) LIKE LOWER(CONCAT('%',?1,'%')) OR"
			+ " LOWER(u.name) LIKE LOWER(CONCAT('%',?1,'%')))")
	public Page<User> findUsers(String searchParam, Pageable paging);

	@Query("SELECT CASE WHEN COUNT(u) = ?2 THEN true ELSE false END FROM User u WHERE u.id IN ?1")
	boolean existsByIdList(List<Long> idList, Long size);

	@Query("SELECT u FROM User u WHERE u.id IN :idList")
	public List<User> getUserListByIdList(List<Long> idList);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE LOWER(u.username)=LOWER(?1) AND u.id != ?2")
	boolean checkExistUsername(String username, Long id);

}
