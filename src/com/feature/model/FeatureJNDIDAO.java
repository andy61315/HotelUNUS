package com.feature.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class FeatureJNDIDAO implements FeatureDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<FeatureVO> findAll() {
		List<FeatureVO> features = new ArrayList<FeatureVO>();
		String sql = "SELECT FEAT_NO, FEAT_NAME FROM FEATURE";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FeatureVO feature = new FeatureVO();
				
				feature.setFeat_no(rs.getString("FEAT_NO"));
				feature.setFeat_name(rs.getString("FEAT_NAME"));
				
				features.add(feature);
				
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
		
		return features;
	}

	@Override
	public FeatureVO findByPk(String pk) {
		FeatureVO feature = new FeatureVO();
		String sql = "SELECT FEAT_NO, FEAT_NAME FROM feature WHERE FEAT_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				feature.setFeat_no(rs.getString("FEAT_NO"));
				feature.setFeat_name(rs.getString("FEAT_NAME"));
				
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
		
		return feature;
	}

	@Override
	public void create(FeatureVO feature) {
		String sql = "INSERT INTO FEATURE (FEAT_NO, FEAT_NAME) " + 
				"VALUES ( 'F'||TO_CHAR(SEQ_FEATURE.NEXTVAL,'FM0000'), ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, feature.getFeat_name());
			
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void update(FeatureVO feature) {
		String sql = "UPDATE FEATURE SET FEAT_NAME=? WHERE FEAT_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, feature.getFeat_name());
			stmt.setString(2, feature.getFeat_no());
			
		} catch (SQLException se) {
			se.printStackTrace();
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
		String sql = "DELETE FROM FEATURE WHERE FEAT_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
		} catch (SQLException se) {
			se.printStackTrace();
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
