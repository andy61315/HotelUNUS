package com.feature.model;

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


public class FeatureJDBCDAO implements FeatureDAO{

	@Override
	public List<FeatureVO> findAll() {
		List<FeatureVO> features = new ArrayList<FeatureVO>();
		String sql = "SELECT FEAT_NO, FEAT_NAME FROM FEATURE";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FeatureVO feature = new FeatureVO();
				
				feature.setFeat_no(rs.getString("FEAT_NO"));
				feature.setFeat_name(rs.getString("FEAT_NAME"));
				
				features.add(feature);
				
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
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				feature.setFeat_no(rs.getString("FEAT_NO"));
				feature.setFeat_name(rs.getString("FEAT_NAME"));
				
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
		
		return feature;
	}

	@Override
	public void create(FeatureVO feature) {
		String sql = "INSERT INTO FEATURE (FEAT_NO, FEAT_NAME) " + 
				"VALUES ( 'F'||TO_CHAR(SEQ_FEATURE.NEXTVAL,'FM0000'), ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			
			conn = DriverManager.getConnection(URL);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, feature.getFeat_name());
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
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
			Class.forName(DRIVER_CLASS);
			
			conn = DriverManager.getConnection(URL);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, feature.getFeat_name());
			stmt.setString(2, feature.getFeat_no());
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
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
		String sql = "DELETE FROM FEATURE where FEAT_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
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

	public static void main(String[] args) {
		FeatureJDBCDAO dao = new FeatureJDBCDAO();
		List<FeatureVO> list = dao.findAll();
//		
//		for (FeatureVO featureVO : list) {
//			String feat_no = featureVO.getFeat_no();
//			String feat_name = featureVO.getFeat_name();
//			
//			System.out.println("{"+ feat_no + ", " + feat_name +"}");
//		}
		
//		FeatureVO featureVO = dao.findByPk("F0002");
//		String feat_no = featureVO.getFeat_no();
//		String feat_name = featureVO.getFeat_name();
//		System.out.println("{"+ feat_no + ", " + feat_name +"}");
	
	}
	
	
	
}
