package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.komsia.kom.domain.UserVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	private UserService userSerivce;

	@Override
	public Map<String, Object> getUserList() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<UserVO> list = userSerivce.getUserList();
		result.put("data", list);
		return result;
	}

}
