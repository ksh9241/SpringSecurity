package co.kr.security.dto;


import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("UserDTO") // 처리안됨.
public class UserDTO {
	
	private int idx;
	private String username;
	private String password;
}
