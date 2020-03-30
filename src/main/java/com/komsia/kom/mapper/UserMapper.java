package com.komsia.kom.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.Role;
import com.komsia.kom.domain.UserVO;

@Repository
@Mapper
public interface UserMapper {

	UserVO selectUserByUserId(String userId);
	
	void joinUser(UserVO userVO);

	void insertAuthUser(Role role);



}
