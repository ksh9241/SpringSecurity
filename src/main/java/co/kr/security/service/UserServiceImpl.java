package co.kr.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.kr.security.dto.UserDTO;
import co.kr.security.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<UserDTO> selectUserList() {
		return this.userMapper.selectUserList();
	}

	@Override
	public int createUser(UserDTO user) {
		String encoder = passwordEncoder.encode(user.getPassword());
		user.setPassword(encoder);
		return this.userMapper.createUser(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userMapper.findByUsername(username);
		List<GrantedAuthority> list = new ArrayList<>();
		if(user.getUsername().equals("ADMIN")) {
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else {
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		return new User(user.getUsername(),user.getPassword(),list);
	}
}
