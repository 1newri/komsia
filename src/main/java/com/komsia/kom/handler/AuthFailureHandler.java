package com.komsia.kom.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.komsia.kom.domain.UserVO;
import com.komsia.kom.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{ 

	private UserMapper userMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String userId = request.getParameter("username");
		
		UserVO user = userMapper.selectUserByUserId(userId);
		if(user == null) {
			log.debug("userId NOT FOUND : {}", userId);
		}
		
		request.setAttribute("result", "fail");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login");
		dispatcher.forward(request, response);
	}
}
