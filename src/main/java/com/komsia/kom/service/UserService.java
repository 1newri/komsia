package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.CustomUserDetails;
import com.komsia.kom.domain.UserVO;
import com.komsia.kom.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements UserDetailsService{
	
	private UserMapper userMapper;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserVO userVO = userMapper.selectUserByUserId(userId);
		if(ObjectUtils.isEmpty(userVO)) {
			throw new UsernameNotFoundException(userId + " is not found");
		}
		
		List<String> userRoles = userVO.getUserRoles().stream().map(userRole
				-> userRole.getRole().getRoleName()).collect(Collectors.toList());
		
		CustomUserDetails userDetails = new CustomUserDetails(userVO, userRoles);
      
		return userDetails;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> joinUser(UserVO userVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
		
		userMapper.joinUser(userVO);
		
		Long userNo = userVO.getUserNo();
		log.debug("=== join User userNo : {}", userNo);
		
		if(!ObjectUtils.isEmpty(userNo)) {
			// 권한 부여 (2:Member)
			insertAuthUser(String.valueOf(userNo), "2");
		}else {
			resCode = ResponseCode.RESPONSE_FAIL;
			resMsg = ResponseCode.RESPONSE_FAIL_MSG;
			
			// TODO Exception
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}

	public Map<String, Object> duplicationUser(String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		UserVO userVO = userMapper.selectUserByUserId(userId);
		if(!ObjectUtils.isEmpty(userVO)) {
			result.put("checkYn", CommonConstant.YN_N);
			result.put("resCode", ResponseCode.DUPLICATE_USER);
			result.put("resMsg", ResponseCode.DUPLICATE_USER_MSG);
		}else {
			result.put("checkYn", CommonConstant.YN_Y);
			result.put("resCode", ResponseCode.RESPONSE_OK);
			result.put("resMsg", ResponseCode.RESPONSE_OK_MSG);
		}
		return result;
	}
	
	public UserVO selectUserByUserId(String userId) {
		return userMapper.selectUserByUserId(userId);
	}

	public List<UserVO> getUserList(UserVO userVO) {
		return userMapper.selectUserList(userVO);
	}

	public void insertAuthUser(String userNo, String auth) {
		userMapper.insertAuthUser(userNo, auth);
	}

	public void deleteAuthUser(String userNo, String auth) {
		userMapper.deleteAuthUser(userNo, auth);
	}

	public Map<String, Object> changePassword(UserVO userVO) {
		Map<String, Object> result = new HashMap<String, Object>();

		UserVO user = userMapper.selectUserByUserId(userVO.getUserId());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(passwordEncoder.matches(userVO.getPassword(), user.getPassword())) {
			userVO.setPassword(passwordEncoder.encode(userVO.getNewPassword()));
			userVO.setModId(userVO.getUserId());
			
			userMapper.updateUserPassword(userVO);
			
			result.put("resCode", ResponseCode.RESPONSE_OK);
			result.put("resMsg", ResponseCode.RESPONSE_OK_MSG);
		}else {
			result.put("resCode", ResponseCode.INVALID_PASSWORD);
			result.put("resMsg", ResponseCode.INVALID_PASSWORD_MSG);
		}
		
		return result;
	}

	public Map<String, Object> changeMyinfo(UserVO userVO) {
		Map<String, Object> result = new HashMap<String, Object>();

		UserVO user = userMapper.selectUserByUserId(userVO.getUserId());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(passwordEncoder.matches(userVO.getPassword(), user.getPassword())) {
			
			userMapper.updateUser(userVO);
			
			result.put("resCode", ResponseCode.RESPONSE_OK);
			result.put("resMsg", ResponseCode.RESPONSE_OK_MSG);
		}else {
			result.put("resCode", ResponseCode.INVALID_PASSWORD);
			result.put("resMsg", ResponseCode.INVALID_PASSWORD_MSG);
		}
		
		return result;
	}
	
	public void updateUserPassword(UserVO userVO) {
		userMapper.updateUserPassword(userVO);
	}

	public List<UserVO> getAdminUserList() {
		return userMapper.getAdminUserList();
	}

	public int getUserListTotal(UserVO userVO) {
		return userMapper.getUserListTotal(userVO);
	}
	
}
