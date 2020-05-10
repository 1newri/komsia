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
	
	void insertAuthUser(@Param(value = "userNo")String userNo, @Param(value = "roleId") String roleId);

	List<UserVO> selectUserList();

	void deleteAuthUser(@Param(value = "userNo")String userNo, @Param(value = "roleId")String auth);

	int selectUser(UserVO userVO);

	void updateUserPassword(UserVO userVO);

	void updateUser(UserVO userVO);

	List<UserVO> getAdminUserList();

}
