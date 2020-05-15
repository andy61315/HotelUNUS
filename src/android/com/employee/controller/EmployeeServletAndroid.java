
package android.com.employee.controller;

import android.com.employee.model.Employee;
import android.com.employee.model.EmployeeDAOImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeServletAndroid extends HttpServlet {
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);

		EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if (action.equals("isEmployee")) {
			String emp_id = jsonObject.get("emp_id").getAsString();
			String emp_password = jsonObject.get("emp_password").getAsString();
			writeText(res, String.valueOf(employeeDAOImpl.isEmployee(emp_id, emp_password)));
			
		} else if (action.equals("isEmp_idExist")) {
			String emp_id = jsonObject.get("emp_id").getAsString();
			writeText(res, String.valueOf(employeeDAOImpl.isEmp_idExist(emp_id)));
			
		} else if (action.equals("add")) {
			Employee employee = (Employee) gson.fromJson(jsonObject.get("employee").getAsString(), Employee.class);
			writeText(res, String.valueOf(employeeDAOImpl.add(employee)));
			
		} else if (action.equals("findById")) {
			String emp_id = jsonObject.get("emp_id").getAsString();
			Employee employee = employeeDAOImpl.findById(emp_id);
			writeText(res, (employee == null) ? "" : gson.toJson(employee));
			
		} else if (action.equals("update")) {
			Employee employee = (Employee) gson.fromJson(jsonObject.get("employee").getAsString(), Employee.class);
			writeText(res, String.valueOf(employeeDAOImpl.update(employee)));
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}
}
