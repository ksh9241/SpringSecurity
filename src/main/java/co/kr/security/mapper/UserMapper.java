package co.kr.security.mapper;

import java.util.List;

import co.kr.security.dto.UserDTO;


public interface UserMapper {

	List<UserDTO> selectUserList();

	int createUser(UserDTO user);

	UserDTO findByUsername(String username);
}
