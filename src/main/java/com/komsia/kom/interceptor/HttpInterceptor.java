package com.komsia.kom.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.service.MenuService;
import com.komsia.kom.service.ResourceMetaSerivce;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class HttpInterceptor extends HandlerInterceptorAdapter{
	
	private MenuService menuService;
	
	private ResourceMetaSerivce resourceMetaSerivce;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) {
		log.debug("==================== BEGIN ====================");
		
		String userId = (String) request.getSession().getAttribute("userId");
		if(StringUtils.isNotEmpty(userId)) {
			log.debug(request.getRequestURI() + ", userID : {}", userId);
		}
		
		return true;
	}
	
	@Override
	public void postHandle( HttpServletRequest request,
							HttpServletResponse response,
							Object handler,
							ModelAndView modelAndView) throws Exception{
		
		log.info("================ Method Executed");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<String> roleNameList = new ArrayList<String>();
		boolean menuAuth = false;
		if(modelAndView != null && authentication != null) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			
			log.debug("{}", authentication.getName());
			
			for(GrantedAuthority grantedAuthority : authorities) {
				roleNameList.add(grantedAuthority.getAuthority());
			}
			
			String userId = (String) request.getSession().getAttribute("userId");
			log.debug("userId : {}", userId);
			
			List<MenuVO> topMenu = resourceMetaSerivce.selectTopMenu(roleNameList);
			List<String> roleIds = resourceMetaSerivce.getRoleIds(roleNameList);
			
			request.setAttribute("userId", userId);
			request.setAttribute("roleIds", roleIds);
			modelAndView.addObject("topMenu", topMenu);
			modelAndView.addObject("roles", authorities);
			
			log.debug("Request URI ===> " + request.getRequestURI());		
			String url = request.getRequestURI();
			
			MenuVO menuVO = menuService.getMenuIdByUrl(url);
			if(!ObjectUtils.isEmpty(menuVO)) {
				request.setAttribute("menuId", menuVO.getMenuId());
				
				
				if(menuService.selectMenuAuth(menuVO.getMenuId(), userId) > 0) {
					menuAuth = true;
				}
				
				request.setAttribute("menuAuth", menuAuth);
			}
		
			Pattern pattern = Pattern.compile("((^\\/[^\\s/\\/]+))");
			Matcher matcher = pattern.matcher(url);
			
			if(matcher.find()) {
				log.debug("url pattern matcher : {}", matcher.group());
				url = matcher.group();
				String menuTitle = menuService.getMenuTitle(url);
				request.setAttribute("menuTitle", menuTitle);
				
				MenuVO menu = menuService.getMenuIdByUrl(url);
				if(!ObjectUtils.isEmpty(menu)) {
					String roleId = "";
					if(!ObjectUtils.isEmpty(roleIds)) {
						roleId = roleIds.get(0);
					}
					List<MenuVO> sideMenu = menuService.getSideMenu(menu.getMenuId(), roleId);
					modelAndView.addObject("sideMenu", sideMenu);
				}
			}else {
				log.debug("url pattern matcher not found !");
			}
			
		}
//		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, 
								Object handler, 
								Exception ex) {
		log.info("================ Method Completed");
	}
}
