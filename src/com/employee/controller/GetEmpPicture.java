package com.employee.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/GetEmpPicture")
public class GetEmpPicture extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String sql = "SELECT EMP_PICTURE FROM EMPLOYEE WHERE EMP_ID=?";
			String emp_id = req.getParameter("emp_id");
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, emp_id);
			
			ResultSet rs = stmt.executeQuery();
					
			
			if (rs.next()) {
				byte[] temp = rs.getBytes("EMP_PICTURE");
				
				// 傳回來是 null 時用 /NoData/none.jpg 來代替
				if (temp == null) {
					InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
					byte[] buf = new byte[in.available()];
					in.read(buf);
					out.write(buf);
					in.close();
				  // 非 null 時從 DB 拿回來
				} else {
					BufferedInputStream bin = new BufferedInputStream(rs.getBinaryStream("EMP_PICTURE"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = bin.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					bin.close();
				}
			}
			
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team1DB");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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