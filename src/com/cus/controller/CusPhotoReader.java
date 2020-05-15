package com.cus.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class CusPhotoReader extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Connection con;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("back-end/customer/image/jpg");
		ServletOutputStream out = res.getOutputStream();//out到瀏覽器
		
		try {
			Statement stmt = con.createStatement();
			
			String cus_Id = req.getParameter("cus_Id").trim();
			ResultSet rs = stmt.executeQuery("Select IDF_PIC from CUSTOMER WHERE CUS_ID='"+cus_Id+"'");
			
			if(rs.next()) {
				BufferedInputStream in = new BufferedInputStream (rs.getBinaryStream("IDF_PIC"));
				byte[] buf = new byte[4*1024];
				int len;
				while((len = in.read(buf)) != -1) {
					out.write(buf,0,len);
				}
				in.close();
			}else {
				//system.out.println("進入else");
				InputStream in = getServletContext().getResourceAsStream("/front-end/customer/NoData/none.jpg");
				byte[] b=new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();	
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/front-end/customer/NoData/none.jpg");
			byte[] b=new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
}
	public void init() throws ServletException{
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			if(con != null) con.close();
		}catch(SQLException e) {
			System.out.print(e);
		}
	}
}
