package com.komsia.kom.interceptor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.service.MenuService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class HttpInterceptor extends HandlerInterceptorAdapter{
	
	private MenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) {
		log.debug("==================== BEGIN ====================");
		log.debug("Request URI ===> " + request.getRequestURI());		
		String url = request.getRequestURI();
		
		List<MenuVO> topMenu = menuService.getTopMenu();
		log.debug("Top Menu List : {}", topMenu);
		
		request.setAttribute("topMenu", topMenu);

		MenuVO menuVO = menuService.getMenuIdByUrl(url);
		if(!ObjectUtils.isEmpty(menuVO)) {
			request.setAttribute("menuId", menuVO.getMenuId());
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
				List<MenuVO> sideMenu = menuService.getSideMenu(menu.getMenuId());
				request.setAttribute("sideMenu", sideMenu);
			}
		}else {
			log.debug("url pattern matcher not found !");
		}
		
		return true;
	}
	
	@Override
	public void postHandle( HttpServletRequest request,
							HttpServletResponse response,
							Object handler,
							ModelAndView modelAndView) {
		log.info("================ Method Executed");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, 
								Object handler, 
								Exception ex) {
		log.info("================ Method Completed");
	}
}
