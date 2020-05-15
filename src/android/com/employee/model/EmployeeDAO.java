package android.com.employee.model;

import android.com.employee.model.Employee;
import java.util.List;

public interface EmployeeDAO {
  boolean isEmployee(String paramString1, String paramString2);
  
  boolean isEmp_idExist(String paramString);
  
  boolean add(Employee paramEmployee);
  
  boolean update(Employee paramEmployee);
  
  boolean delete(String paramString);
  
  Employee findById(String paramString);
  
  List<Employee> getAll();
}


/* Location:              D:\recovery\DA106G1_0415\WEB-INF\classes.zip!\classes\android\com\employee\model\EmployeeDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */