package com.employee.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployeeJNDIDAO implements EmployeeDAO{
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
	public List<EmployeeVO> findAll() {
		List<EmployeeVO> employeeVOs = new LinkedList<EmployeeVO>();
		String sql = "SELECT * FROM EMPLOYEE";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVO employeeVO = new EmployeeVO();
				
				employeeVO.setDep_no(rs.getString("dep_no"));
				employeeVO.setEmp_email(rs.getString("emp_email"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_password(rs.getString("emp_password"));
				employeeVO.setEmp_phone(rs.getString("emp_phone"));
				employeeVO.setEmp_status(rs.getInt("emp_status"));
				employeeVO.setEmp_picture(rs.getBytes("emp_picture"));
				
				employeeVOs.add(employeeVO);
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
		return employeeVOs;
	}

	@Override
	public EmployeeVO findByPk(String pk) {
		EmployeeVO employeeVO = null;
		String sql = "SELECT * FROM EMPLOYEE where emp_id=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				employeeVO = new EmployeeVO();
				
				employeeVO.setDep_no(rs.getString("dep_no"));
				employeeVO.setEmp_email(rs.getString("emp_email"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_password(rs.getString("emp_password"));
				employeeVO.setEmp_phone(rs.getString("emp_phone"));
				employeeVO.setEmp_status(rs.getInt("emp_status"));
				employeeVO.setEmp_picture(rs.getBytes("emp_picture"));
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
		return employeeVO;
	}

	
	
	@Override
	public EmployeeVO findByEmail(String emp_email) {
		EmployeeVO employeeVO = null;
		String sql = "SELECT * FROM EMPLOYEE where EMP_EMAIL=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emp_email);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				employeeVO = new EmployeeVO();
				
				employeeVO.setDep_no(rs.getString("dep_no"));
				employeeVO.setEmp_email(rs.getString("emp_email"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_password(rs.getString("emp_password"));
				employeeVO.setEmp_phone(rs.getString("emp_phone"));
				employeeVO.setEmp_status(rs.getInt("emp_status"));
				employeeVO.setEmp_picture(rs.getBytes("emp_picture"));
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
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> findByStatus(int status) {
		List<EmployeeVO> employeeVOs = new LinkedList<EmployeeVO>();
		String sql = "SELECT * FROM EMPLOYEE where emp_status=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, status);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVO employeeVO = new EmployeeVO();
				
				employeeVO.setDep_no(rs.getString("dep_no"));
				employeeVO.setEmp_email(rs.getString("emp_email"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_password(rs.getString("emp_password"));
				employeeVO.setEmp_phone(rs.getString("emp_phone"));
				employeeVO.setEmp_status(rs.getInt("emp_status"));
				employeeVO.setEmp_picture(rs.getBytes("emp_picture"));
				
				employeeVOs.add(employeeVO);
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
		return employeeVOs;
	}

	@Override
	public void insert(EmployeeVO employee) {
		String sql = "INSERT INTO EMPLOYEE (EMP_ID, DEP_NO, EMP_NAME, EMP_PHONE, EMP_EMAIL, "
				+ "EMP_PASSWORD, EMP_STATUS, EMP_PICTURE) "
				+ "VALUES ('EMP'||TO_CHAR(SEQ_EMPLOYEE_ID.nextval,'FM0000000'),?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, employee.getDep_no());
			stmt.setString(2, employee.getEmp_name());
			stmt.setString(3, employee.getEmp_phone());
			stmt.setString(4, employee.getEmp_email());
			stmt.setString(5, employee.getEmp_password());
			stmt.setInt(6, employee.getEmp_status());
			stmt.setBytes(7, employee.getEmp_picture());
			
			stmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(EmployeeVO employee) {
		String sql = "UPDATE EMPLOYEE SET DEP_NO=?, EMP_NAME=?, EMP_PHONE=?, EMP_EMAIL=?, "
				+ "EMP_STATUS=?, EMP_PICTURE=? WHERE EMP_ID=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, employee.getDep_no());
			stmt.setString(2, employee.getEmp_name());
			stmt.setString(3, employee.getEmp_phone());
			stmt.setString(4, employee.getEmp_email());
			stmt.setInt(5, employee.getEmp_status());
			stmt.setBytes(6, employee.getEmp_picture());
			stmt.setString(7, employee.getEmp_id());
			
			stmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void updateWithoutPic(EmployeeVO employee) {
		String sql = "UPDATE EMPLOYEE SET DEP_NO=?, EMP_NAME=?, EMP_PHONE=?, EMP_EMAIL=?, "
				+ "EMP_STATUS=? WHERE EMP_ID=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, employee.getDep_no());
			stmt.setString(2, employee.getEmp_name());
			stmt.setString(3, employee.getEmp_phone());
			stmt.setString(4, employee.getEmp_email());
			stmt.setInt(5, employee.getEmp_status());
			stmt.setString(6, employee.getEmp_id());
			
			stmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void updateResign(String pk) {
		String sql = "UPDATE EMPLOYEE SET EMP_STATUS=0 WHERE EMP_ID=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			stmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		String sql = "DELETE FROM EMPLOYEE WHERE EMP_ID=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			stmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
