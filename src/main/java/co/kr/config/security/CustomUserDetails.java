package co.kr.config.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial") // 컴파일러가 일반적으로 경고하는 내용 중 어떤 경고를 제외시킬지 옵션을 줄 수 있다.
public class CustomUserDetails implements UserDetails {
	private String ID;
	private String PASSWORD;
	private String AUTHORITY;
	private boolean ENABLED;
	private String NAME;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(AUTHORITY));
		return auth;
	}

	@Override
	public String getPassword() {
		return PASSWORD;
	}

	@Override
	public String getUsername() {
		return ID;
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

	@Override
	public boolean isEnabled() {
		return ENABLED;
	}
	
	public String getName() {
		return NAME;
	}
	
	public void setName(String name) {
		this.NAME = name;
	}

}
