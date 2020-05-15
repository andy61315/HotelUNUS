package com.employee.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EmpSignedInFilter
 */
public class EmpSignedInFilter implements Filter {
	private FilterConfig config;
	
	public void init(FilterConfig fConfig) throws ServletException {
		fConfig = config;
	}
	
	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 登入狀態 true -> 登入中, false -> 尚未登入
		boolean signedIn = req.getSession().getAttribute("emp_id") != null;
		System.out.println("登入中? : "+signedIn);
		
		// 登入頁面
		String signinPageURL = req.getContextPath() + "/empSignin.jsp";
		System.out.println("轉交網址"+signinPageURL);
		
		if (!signedIn) {
			res.sendRedirect(signinPageURL);
		}
		
		chain.doFilter(request, response);
	}

}
