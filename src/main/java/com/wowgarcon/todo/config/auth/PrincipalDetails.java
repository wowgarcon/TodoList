package com.wowgarcon.todo.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wowgarcon.todo.domain.UserDAO;

//시큐리티가 /login 주소 요청을 낚아채서 로그인을 진행시킨다.
//로그인 진행이 완료되면 session을 만들어준다.(Security ContextHolder)
//오브젝트 => Authentication 타입 객체
//Autentication 안에 User 정보가 있어야 함.
//User 오브젝트 타입 => UserDetails 타입 객체

//Security Session => Authentication => UserDetails(PrincipalDetails)

public class PrincipalDetails implements UserDetails{

	private UserDAO user;
	
	public PrincipalDetails(UserDAO user) {
		this.user = user;
	}
	
	//해당 User의 권한을 리턴하는 곳.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//장기간 비로그인 시 휴면계정
	@Override
	public boolean isEnabled() {
		return true;
	}

}
