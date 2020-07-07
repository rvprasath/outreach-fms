package com.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String userName, String password);

	User findByEmail(String username);

	Page<User> findByRole(int i, Pageable paging);

	Page<User> findByRoleIn(int [] i, Pageable paging);
	
	List<User> findByRole(int i);

//	Page<User> findByRoleOrderByEmailAsc(int i, Pageable paging);

}
