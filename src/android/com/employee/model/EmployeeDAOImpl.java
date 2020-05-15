/*     */ package android.com.employee.model;
/*     */ 
/*     */ import android.com.employee.model.Employee;
/*     */ import android.com.employee.model.EmployeeDAO;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EmployeeDAOImpl
/*     */   implements EmployeeDAO
/*     */ {
/*     */   private static final String INSERT_STMT = "INSERT INTO employee (emp_id, dep_no, emp_name, emp_phone, emp_email, emp_password, emp_status) VALUES ('EMP'||LPAD(TO_CHAR(SEQ_EMPLOYEE_ID.NEXTVAL), 7, '0'), ?, ?, ?, ?, ?, ?)";
/*     */   private static final String UPDATE_STMT = "UPDATE employee set dep_no=?, emp_name=?, emp_phone=?, emp_email=?, emp_password=?, emp_status=? where emp_id = ?";
/*     */   private static final String FIND_BY_ID_PASWD = "SELECT * FROM employee WHERE emp_id = ? AND emp_password = ?";
/*     */   private static final String FIND_BY_ID = "SELECT * FROM employee WHERE emp_id = ?";
/*     */   private static final String CHECK_ID_EXIST = "SELECT emp_id FROM employee WHERE emp_id = ?";
/*     */   
/*     */   public EmployeeDAOImpl() {
/*     */     try {
/*  27 */       Class.forName("oracle.jdbc.driver.OracleDriver");
/*  28 */     } catch (ClassNotFoundException e) {
/*  29 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmployee(String emp_id, String emp_password) {
/*  35 */     Connection conn = null;
/*  36 */     PreparedStatement ps = null;
/*  37 */     boolean isMember = false;
/*     */     try {
/*  39 */       conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DA106G1", "DA106G1");
/*  40 */       ps = conn.prepareStatement("SELECT * FROM employee WHERE emp_id = ? AND emp_password = ?");
/*  41 */       ps.setString(1, emp_id);
/*  42 */       ps.setString(2, emp_password);
/*  43 */       ResultSet rs = ps.executeQuery();
/*  44 */       isMember = rs.next() && (rs.getInt("emp_status")) == 1;  //rs.getInt("emp_status"): 取得員工狀態，1在職 0離職
/*  45 */       return isMember;
/*  46 */     } catch (SQLException e) {
/*  47 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  50 */         if (ps != null) {
/*  51 */           ps.close();
/*     */         }
/*  53 */         if (conn != null) {
/*  54 */           conn.close();
/*     */         }
/*  56 */       } catch (SQLException e) {
/*  57 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*  60 */     return isMember;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmp_idExist(String emp_id) {
/*  65 */     Connection conn = null;
/*  66 */     PreparedStatement ps = null;
/*  67 */     boolean isUserIdExist = false;
/*     */     try {
/*  69 */       conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DA106G1", "DA106G1");
/*  70 */       ps = conn.prepareStatement("SELECT emp_id FROM employee WHERE emp_id = ?");
/*  71 */       ps.setString(1, emp_id);
/*  72 */       ResultSet rs = ps.executeQuery();
/*  73 */       isUserIdExist = rs.next();
/*  74 */       return isUserIdExist;
/*  75 */     } catch (SQLException e) {
/*  76 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  79 */         if (ps != null) {
/*  80 */           ps.close();
/*     */         }
/*  82 */         if (conn != null) {
/*  83 */           conn.close();
/*     */         }
/*  85 */       } catch (SQLException e) {
/*  86 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*  89 */     return isUserIdExist;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Employee employee) {
/*  94 */     Connection con = null;
/*  95 */     PreparedStatement pstmt = null;
/*  96 */     boolean isAdded = false;
/*     */     
/*     */     try {
/*  99 */       con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DA106G1", "DA106G1");
/* 100 */       pstmt = con.prepareStatement("INSERT INTO employee (emp_id, dep_no, emp_name, emp_phone, emp_email, emp_password, emp_status) VALUES ('EMP'||LPAD(TO_CHAR(SEQ_EMPLOYEE_ID.NEXTVAL), 7, '0'), ?, ?, ?, ?, ?, ?)");
/*     */       
/* 102 */       pstmt.setString(1, employee.getDep_no());
/* 103 */       pstmt.setString(2, employee.getEmp_name());
/* 104 */       pstmt.setString(3, employee.getEmp_phone());
/* 105 */       pstmt.setString(4, employee.getEmp_email());
/* 106 */       pstmt.setString(5, employee.getEmp_password());
/* 107 */       pstmt.setInt(6, employee.getEmp_status());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 113 */       int count = pstmt.executeUpdate();
/* 114 */       isAdded = (count > 0);
/*     */     
/*     */     }
/* 117 */     catch (SQLException se) {
/* 118 */       throw new RuntimeException("A database error occured. " + se.getMessage());
/*     */     } finally {
/*     */       
/* 121 */       if (pstmt != null) {
/*     */         try {
/* 123 */           pstmt.close();
/* 124 */         } catch (SQLException se) {
/* 125 */           se.printStackTrace(System.err);
/*     */         } 
/*     */       }
/* 128 */       if (con != null) {
/*     */         try {
/* 130 */           con.close();
/* 131 */         } catch (Exception e) {
/* 132 */           e.printStackTrace(System.err);
/*     */         } 
/*     */       }
/*     */     } 
/* 136 */     return isAdded;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean update(Employee employee) {
/* 141 */     Connection con = null;
/* 142 */     PreparedStatement pstmt = null;
/* 143 */     boolean isUpdated = false;
/*     */     
/*     */     try {
/* 146 */       con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DA106G1", "DA106G1");
/* 147 */       pstmt = con.prepareStatement("UPDATE employee set dep_no=?, emp_name=?, emp_phone=?, emp_email=?, emp_password=?, emp_status=? where emp_id = ?");
/*     */       
/* 149 */       pstmt.setString(1, employee.getDep_no());
/* 150 */       pstmt.setString(2, employee.getEmp_name());
/* 151 */       pstmt.setString(3, employee.getEmp_phone());
/* 152 */       pstmt.setString(4, employee.getEmp_email());
/* 153 */       pstmt.setString(5, employee.getEmp_password());
/* 154 */       pstmt.setInt(6, employee.getEmp_status());
/* 155 */       pstmt.setString(7, employee.getEmp_id());
/*     */       
/* 157 */       int count = pstmt.executeUpdate();
/* 158 */       isUpdated = (count > 0);
/*     */     
/*     */     }
/* 161 */     catch (SQLException se) {
/* 162 */       throw new RuntimeException("A database error occured. " + se.getMessage());
/*     */     } finally {
/*     */       
/* 165 */       if (pstmt != null) {
/*     */         try {
/* 167 */           pstmt.close();
/* 168 */         } catch (SQLException se) {
/* 169 */           se.printStackTrace(System.err);
/*     */         } 
/*     */       }
/* 172 */       if (con != null) {
/*     */         try {
/* 174 */           con.close();
/* 175 */         } catch (Exception e) {
/* 176 */           e.printStackTrace(System.err);
/*     */         } 
/*     */       }
/*     */     } 
/* 180 */     return isUpdated;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean delete(String emp_id) {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Employee findById(String emp_id) {
/* 191 */     Employee employee = null;
/* 192 */     Connection con = null;
/* 193 */     PreparedStatement pstmt = null;
/* 194 */     ResultSet rs = null;
/*     */ 
/*     */     
/*     */     try {
/* 198 */       con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DA106G1", "DA106G1");
/* 199 */       pstmt = con.prepareStatement("SELECT * FROM employee WHERE emp_id = ?");
/*     */       
/* 201 */       pstmt.setString(1, emp_id);
/*     */       
/* 203 */       rs = pstmt.executeQuery();
/*     */       
/* 205 */       while (rs.next()) {
/* 206 */         employee = new Employee();
/*     */         
/* 208 */         employee = new Employee();
/* 209 */         employee.setEmp_id(rs.getString(1));
/* 210 */         employee.setDep_no(rs.getString(2));
/* 211 */         employee.setEmp_name(rs.getString(3));
/* 212 */         employee.setEmp_phone(rs.getString(4));
/* 213 */         employee.setEmp_email(rs.getString(5));
/* 214 */         employee.setEmp_password(rs.getString(6));
/* 215 */         employee.setEmp_status(rs.getInt(7));
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 222 */     catch (SQLException se) {
/* 223 */       throw new RuntimeException("A database error occured. " + se.getMessage());
/*     */     } finally {
/*     */       
/* 226 */       if (rs != null) {
/*     */         try {
/* 228 */           rs.close();
/* 229 */         } catch (SQLException se) {
/* 230 */           se.printStackTrace(System.err);
/*     */         } 
/*     */       }
/* 233 */       if (pstmt != null) {
/*     */         try {
/* 235 */           pstmt.close();
/* 236 */         } catch (SQLException se) {
/* 237 */           se.printStackTrace(System.err);
/*     */         } 
/*     */       }
/* 240 */       if (con != null) {
/*     */         try {
/* 242 */           con.close();
/* 243 */         } catch (Exception e) {
/* 244 */           e.printStackTrace(System.err);
/*     */         } 
/*     */       }
/*     */     } 
/* 248 */     return employee;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Employee> getAll() {
/* 254 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\recovery\DA106G1_0415\WEB-INF\classes.zip!\classes\android\com\employee\model\EmployeeDAOImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */