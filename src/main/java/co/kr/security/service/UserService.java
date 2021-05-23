package co.kr.security.service;

import java.util.List;

import co.kr.security.dto.UserDTO;

public interface UserService {

	List<UserDTO> selectUserList();

	int createUser(UserDTO user);

}
