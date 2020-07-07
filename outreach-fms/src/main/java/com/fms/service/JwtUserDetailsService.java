package com.fms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fms.config.WebSecurityConfig;
import com.fms.dao.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userDao;

	@Autowired
	WebSecurityConfig webSecurityConfig;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.fms.entity.User user = userDao.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		} else {
			return new User(user.getEmail(), webSecurityConfig.passwordEncoder().encode(user.getPassword()),
					new ArrayList<>());
		}
	}

}