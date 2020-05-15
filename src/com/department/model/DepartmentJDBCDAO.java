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


public class DepartmentJDBCDAO implements DepartmentDAO{

	@Override
	public List<DepartmentVO> findAll() {
		List<DepartmentVO> departments = new ArrayList<DepartmentVO>();
		String sql = "SELECT DEP_NO, DEP_NAME FROM DEPARTMENT";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				DepartmentVO department = new DepartmentVO();
				
				department.setDep_no(rs.getString("DEP_NO"));
				department.setDep_name(rs.getNString("DEP_NAME"));
				
				departments.add(department);
			}
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
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
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				department.setDep_no(rs.getString("DEP_NO"));
				department.setDep_name(rs.getNString("DEP_NAME"));
				
			}
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
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
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, department.getDep_name());
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
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
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, department.getDep_name());
			stmt.setString(1, department.getDep_no());
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
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
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
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
