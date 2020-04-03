package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.UserVO;

@Repository
@Mapper
public interface UserMapper {

	UserVO selectUserByUserId(String userId);
	
	void joinUser(UserVO userVO);
	
	void insertAuthUser(@Param(value = "userNo")Long userNo, @Param(value = "roleId") String roleId);

	List<UserVO> selectUserList();

}
