package com.komsia.kom.domain;

import lombok.Data;

@Data
public class Authorities {

	private Integer id;
	private UserVO userVO;
	private Role role;
}
