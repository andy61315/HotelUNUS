package com.roomtypepicture.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.xml.sax.InputSource;

/**
 * Servlet implementation class GetRoomTypePicture
 */
//@WebServlet("/GetRoomTypePicture")
public class GetRoomTypePicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SELECT_STMT= "SELECT ROOM_TYPE_PIC FROM ROOM_TYPE_PICTURE WHERE ROOM_TYPE_PICTURE_NO = ?";
	private static final String SELECT_ONE_STMT= "SELECT ROOM_TYPE_PIC FROM ROOM_TYPE_PICTURE WHERE ROOM_TYPE_NO = ? and rownum = 1 ";
	Connection con;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		req.setCharacterEncoding("UTF-8");
		ServletOutputStream out = res.getOutputStream();
		
		String action = req.getParameter("action");
		
		
		PreparedStatement stmt = null; 
		try {
			if("getOneForDisplay".equals(action)) {//根據房型選第一張圖片
				stmt = con.prepareStatement(SELECT_ONE_STMT);
				String room_Type_No = req.getParameter("room_Type_No").trim();
				stmt.setString(1, room_Type_No);
				System.out.println("room_Type_No = " + room_Type_No);
			}else {//顯示圖片編號顯示圖片
				stmt = con.prepareStatement(SELECT_STMT);
				String room_Type_Pic_No = req.getParameter("room_Type_Picture_No").trim();
				stmt.setString(1, room_Type_Pic_No);
			}
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("room_Type_Pic"));
				byte[] buf = new byte[4*1024];
				int len;
				while((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			}else {
				InputStream in=getServletContext().getResourceAsStream("/NoData/noimagefound.jpg");
				byte[] b=new byte[in.available()];
				in.read(b);//把資料讀進b
				out.write(b);//把b所有資料寫到destination
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		res.getWriter().append("Served at: ").append(req.getContextPath());
	}
public void init() throws ServletException {
		
		//與DBGifReader2的差別:改成連線池
		
    	try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
