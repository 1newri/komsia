package com.komsia.kom.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.komsia.kom.domain.CustomUserDetails;
import com.komsia.kom.service.ResourceMetaSerivce;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private ResourceMetaSerivce ResourceMetaSerivce;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		log.debug("########### Login ###########");
		
		HttpSession session = request.getSession();
		
		if(!ObjectUtils.isEmpty(session)) {
			
			List<String> roleNameList = new ArrayList<String>();
			List<String> roleList = new ArrayList<String>();
			
			for(GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				roleNameList.add(grantedAuthority.getAuthority());
			}
			
			CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
			String userId = user.getUsername();
			
			roleList = ResourceMetaSerivce.getRoleIds(roleNameList);
			session.setAttribute("userId", userId);
			session.setAttribute("roleIdList", roleList);
			session.setAttribute("roleNameList", roleNameList);
			
			String redirectUrl = (String) session.getAttribute("prevPage");
			if (!StringUtils.isEmpty(redirectUrl)) {
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
		}
	}
}
