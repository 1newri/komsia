package com.komsia.kom.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.Role;
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
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role role : userVO.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleNm()));
		}
		
		CustomUserDetails userDetails = new CustomUserDetails(userId, userVO.getPassword(), authorities);
		userDetails.setUserName(userVO.getUserName());
		userDetails.setUserNo(userVO.getUserNo());
      
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
			// 권한 부여
			Role role = new Role();
			role.setUserNo(userNo);
			role.setRoleCd(CommonConstant.ROLE_M);
			userMapper.insertAuthUser(role);
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

}
