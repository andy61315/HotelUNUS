package com.cus.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cus.model.CustomerService;
import com.cus.model.CustomerVO;

public class LoginFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		// 【取得 session】
		HttpSession session = req.getSession();
//		System.out.println("session:"+session);
//		System.out.println("req:"+req);
		// 【從 session 判斷此user是否登入過】
		Object account = session.getAttribute("customerVO");
		CustomerVO cusVO=(CustomerVO)account;
		String queryString = req.getQueryString();
		String parameters = "";
			
		if(queryString != null) {
			parameters = "?" + queryString;
		}
		
		if (account == null) {
			session.setAttribute("location", req.getRequestURI() + parameters);
			res.sendRedirect(req.getContextPath() + "/front-end/homepage/Login.jsp");
			return;
		} else if (account!=null) {
			Integer cus_Ck = cusVO.getCus_Ck();
			if(cus_Ck.equals(0)) {
				session.setAttribute("location", req.getRequestURI() + parameters);
				res.sendRedirect(req.getContextPath() + "/front-end/customer/MailCheck.jsp");
				errorMsgs.add("您的帳號尚未認證，請先驗證後再繼續。");
				return;
			}
			
			chain.doFilter(req, res);
		}
	}

}