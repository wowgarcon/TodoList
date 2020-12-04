package com.wowgarcon.todo.config.auth;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wowgarcon.todo.domain.UserDAO;
import com.wowgarcon.todo.repository.UserRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.java.Log;

//WebSecurityConfig 설정에서 loginProcessingUrl("/login")
//login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	//시큐리티 session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("=== Load User By Username ===");
		
		UserDAO user = userRepository.findByUsername(username);
		
		if(user == null) {
			log.debug("=== No user found width userId ===");
			throw new UsernameNotFoundException(username);
		}
		
		System.out.println(user);
		return new PrincipalDetails(user);
	}
	
}
