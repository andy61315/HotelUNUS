/*    */
package android.com.employee.model;

/*    */
/*    */ import java.io.Serializable;

/*    */
/*    */
/*    */ public class Employee/*    */ implements Serializable
/*    */ {
	/*    */ private String emp_id;
	/*    */ private String dep_no;
	/*    */ private String emp_name;
	/*    */ private String emp_phone;
	/*    */ private String emp_email;
	/*    */ private String emp_password;
	/*    */ private Integer emp_status;

	/*    */
	/*    */ public Employee() {

	}

	/*    */
	/*    */ public Employee(String emp_id, String dep_no, String emp_name, String emp_phone, String emp_email,
			String emp_password, int emp_status) {
		/* 20 */ this.emp_id = emp_id;
		/* 21 */ this.dep_no = dep_no;
		/* 22 */ this.emp_name = emp_name;
		/* 23 */ this.emp_phone = emp_phone;
		/* 24 */ this.emp_email = emp_email;
		/* 25 */ this.emp_password = emp_password;
		/* 26 */ this.emp_status = Integer.valueOf(emp_status);
		/*    */ }

	/*    */
	/*    */ public String getEmp_id() {
		/* 30 */ return this.emp_id;
		/*    */ }

	/*    */
	/*    */ public void setEmp_id(String emp_id) {
		/* 34 */ this.emp_id = emp_id;
		/*    */ }

	/*    */
	/*    */ public String getDep_no() {
		/* 38 */ return this.dep_no;
		/*    */ }

	/*    */
	/*    */ public void setDep_no(String dep_no) {
		/* 42 */ this.dep_no = dep_no;
		/*    */ }

	/*    */
	/*    */ public String getEmp_name() {
		/* 46 */ return this.emp_name;
		/*    */ }

	/*    */
	/*    */ public void setEmp_name(String emp_name) {
		/* 50 */ this.emp_name = emp_name;
		/*    */ }

	/*    */
	/*    */ public String getEmp_phone() {
		/* 54 */ return this.emp_phone;
		/*    */ }

	/*    */
	/*    */ public void setEmp_phone(String emp_phone) {
		/* 58 */ this.emp_phone = emp_phone;
		/*    */ }

	/*    */
	/*    */ public String getEmp_email() {
		/* 62 */ return this.emp_email;
		/*    */ }

	/*    */
	/*    */ public void setEmp_email(String emp_email) {
		/* 66 */ this.emp_email = emp_email;
		/*    */ }

	/*    */
	/*    */ public String getEmp_password() {
		/* 70 */ return this.emp_password;
		/*    */ }

	/*    */
	/*    */ public void setEmp_password(String emp_password) {
		/* 74 */ this.emp_password = emp_password;
		/*    */ }

	/*    */
	/*    */ public int getEmp_status() {
		/* 78 */ return this.emp_status.intValue();
		/*    */ }

	/*    */
	/*    */ public void setEmp_status(int emp_status) {
		/* 82 */ this.emp_status = Integer.valueOf(emp_status);
		/*    */ }
	/*    */ }

/*
 * Location:
 * D:\recovery\DA106G1_0415\WEB-INF\classes.zip!\classes\android\com\employee\
 * model\Employee.class Java compiler version: 8 (52.0) JD-Core Version: 1.1.3
 */