package com.department.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DepartmentJNDIDAO implements DepartmentDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team1DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<DepartmentVO> findAll() {
		List<DepartmentVO> departments = new ArrayList<DepartmentVO>();
		String sql = "SELECT DEP_NO, DEP_NAME FROM DEPARTMENT";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				DepartmentVO department = new DepartmentVO();
				
				department.setDep_no(rs.getString("DEP_NO"));
				department.setDep_name(rs.getNString("DEP_NAME"));
				
				departments.add(department);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return departments;
	}

	@Override
	public DepartmentVO findByPk(String pk) {
		DepartmentVO department = new DepartmentVO();
		String sql = "SELECT DEP_NO, DEP_NAME FROM DEPARTMENT WHERE=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				department.setDep_no(rs.getString("DEP_NO"));
				department.setDep_name(rs.getNString("DEP_NAME"));
				
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return department;
	}

	@Override
	public void create(DepartmentVO department) {
		String sql = "INSERT INTO DEPARTMENT(DEP_NO, DEP_NAME) " + 
				"VALUES('DEP'||TO_CHAR(SEQ_DEP_NO.nextval,'FM00'),?);";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, department.getDep_name());
			
		} catch (SQLException qe) {
			qe.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(DepartmentVO department) {
		String sql = "UPDATE DEPARTMENT SET DEP_NAME=? WHERE DEP_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, department.getDep_name());
			stmt.setString(1, department.getDep_no());
			
		} catch (SQLException qe) {
			qe.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String pk) {
		String sql = "DELETE FROM DEPARTMENT WHERE DEP_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
		} catch (SQLException qe) {
			qe.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

}
