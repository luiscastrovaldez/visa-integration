package com.visa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@RequestMapping(value = "/")
public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub

		/*
		 * HttpSession session = request.getSession();
		 * 
		 * if (session != null) { session.setAttribute("isNew", Boolean.TRUE); }
		 * else {
		 * 
		 * }
		 */

		return super.preHandle(request, response, handler);
	}

}
