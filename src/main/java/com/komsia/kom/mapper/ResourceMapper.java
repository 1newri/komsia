package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.AuthVO;
import com.komsia.kom.domain.MenuAuthVO;
import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.Role;

@Repository
@Mapper
public interface ResourceMapper {

	List<String> selectRoleIdList(@Param(value="role_name") List<String> roleNameList);

	List<MenuVO> selectTopMenu(@Param(value="role_name") List<String> roleNameList);

	List<Role> selectRoleList();

	List<AuthVO> selectAuthUserList(@Param(value="roleId") String roleId, @Param(value="searchText") String searchText);

	List<AuthVO> selectNotAuthUserList(@Param(value="roleId") String roleId, @Param(value="searchText") String searchText);

	List<MenuAuthVO> selectMenuAuthList(int roleId);

	void deleteMenuAuth(int roleId);

	void insertMenuAuth(MenuAuthVO menuAuthVO);

}
