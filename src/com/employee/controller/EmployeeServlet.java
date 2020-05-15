package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

import common.InputStreamUtils;
import common.MailService;
import common.PasswordMD5;
import oracle.net.aso.p;

@WebServlet("/employee/emp.do")
@MultipartConfig(fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JSONArray fakeData = new JSONArray();
	private boolean firstTime = true;
	
	public EmployeeServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		JSONObject output = new JSONObject();
		Map<Integer, String> emp_status_map = (Map<Integer, String>) req.getServletContext().getAttribute("emp_status");
		System.out.println("action:"+action);
		
		
		if ("getData".equals(action)) {
			String pageNo = req.getParameter("pageNo");
			String pageSize = req.getParameter("pageSize");
			String orderBy = req.getParameter("orderBy");
			String orderType = req.getParameter("orderType");
			System.out.println("pageNo="+pageNo);
			System.out.println("pageSize="+pageSize);
			System.out.println("orderBy="+orderBy);
			System.out.println("orderType="+orderType);
			String like = req.getParameter("like");
			like = like.trim();
			System.out.println("like="+like);
			/* ===== 以下從資料庫下ＳＱＬ指令查找 ===== */
			// Make fake data
//			if(firstTime){
				// 每次進來都從 DB 抓最新的資料出來
				List<EmployeeVO> list = new EmployeeService().getAll();
				fakeData = new JSONArray(list);
				
				for (int i = 0; i < fakeData.length(); i++) {
					JSONObject obj = fakeData.getJSONObject(i);
					String empImg = "<img src='" + req.getContextPath() + "/GetEmpPicture?emp_id=" + obj.getString("emp_id") + "' style='width: 100%; height: auto;'>";
					Integer status = obj.getInt("emp_status");
					
					obj.put("empImg", empImg);
					obj.put("emp_status_name", emp_status_map.get(status));
				}
//				firstTime = false;
//			}
			
			
			// orderBy and orderType
			JSONArray sortedData = new JSONArray();
			List<JSONObject> jsonValues = new ArrayList<JSONObject>();
			for (int i = 0; i < fakeData.length(); i++) {
				jsonValues.add(fakeData.getJSONObject(i));
			}
			Collections.sort(jsonValues, new Comparator<JSONObject>() {
				@Override
				public int compare(JSONObject a, JSONObject b) {
					if("emp_status".equals(orderBy)){
						Integer valA = (Integer) a.get(orderBy);
						Integer valB = (Integer) b.get(orderBy);
						if ("desc".equals(orderType)) 
							return -valA.compareTo(valB);
						else 
							return valA.compareTo(valB);
					} else {
						String valA = (String) a.get(orderBy);
						String valB = (String) b.get(orderBy);
						if ("desc".equals(orderType)) 
							return -valA.compareTo(valB);
						else 
							return valA.compareTo(valB);
					}
				}
			});
			for (int i = 0; i < fakeData.length(); i++) {
				sortedData.put(jsonValues.get(i));
			}

			// like
			JSONArray filterData = new JSONArray();
			if (!"".equals(like)) {
				for (int i = 0; i < sortedData.length(); i++) {
					JSONObject obj = (JSONObject) sortedData.get(i);
					Set<String> keys = obj.keySet();
					Iterator<String> a = keys.iterator();
					while (a.hasNext()) {
						String key = (String) a.next();
						String value = new String();
						if("emp_status".equals(key)) {
							Integer tmp = (Integer) obj.get(key);
							value = tmp.toString();
						} else
							value = (String) obj.get(key);
						if (value.contains(like)) {
							filterData.put(sortedData.get(i));
							break;
						}
					}
				}
			} else {
				filterData = sortedData;
			}
			// total
			output.put("total", filterData.length());

			// firstNo and pageSize
			Integer no = Integer.parseInt(pageNo);
			Integer page = Integer.parseInt(pageSize);
			int startNo = (no > 1) ? (no - 1) * page : 0;
			
			JSONArray finalData = new JSONArray();
			int endNo = (filterData.length() >= (startNo + page)) ? (startNo + page) : filterData.length();
			for (int i = startNo; i < endNo; i++) {
				finalData.put(filterData.getJSONObject(i));
			}
			
			for (int i = 0; i < finalData.length(); i++) {
				JSONObject obj = finalData.getJSONObject(i);
				String empImg = "<img src='" + req.getContextPath() + "/GetEmpPicture?emp_id=" + obj.getString("emp_id") + "' style='width: 100%; height: auto;'>";
				Integer status = obj.getInt("emp_status");
				
				obj.put("empImg", empImg);
				obj.put("emp_status_name", emp_status_map.get(status));
			}
			
			
			
			output.put("rows", finalData);

//			// delay 1 sec
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}
		} else if("insert".equals(action)){
			PasswordMD5 passwordMD5 = new PasswordMD5();
			
			
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			String dep_no = req.getParameter("dep_no");
			String emp_name = new String(req.getParameter("emp_name").getBytes("iso-8859-1"), "UTF-8");
			String emp_phone = req.getParameter("emp_phone");
			String emp_email = req.getParameter("emp_email");
			Integer emp_status = Integer.valueOf(req.getParameter("emp_status"));
			byte[] emp_picture = null;
			System.out.println("getContentType : "+req.getContentType());
			String emp_picture1 = req.getParameter("emp_picture");
			System.out.println("img : "+emp_picture1);
			try {
				// 圖片處理
				Part part = req.getPart("emp_picture");
				System.out.println("part : " + part);
				if (part != null) {
					emp_picture = InputStreamUtils.InputStreamTOByte(part);
				} else {
					emp_picture = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(dep_no);
			System.out.println(emp_name);
			System.out.println(emp_phone);
			System.out.println(emp_email);
			System.out.println(emp_status);
			String emp_password = passwordMD5.getRandomPassword(10);
			System.out.println("pwd : " + emp_password);
			
			// 轉交服務層
			EmployeeService employeeSvc = new EmployeeService();
			EmployeeVO employeeVO = employeeSvc
					.addEmp(dep_no, emp_name, emp_phone, emp_email, emp_password, emp_status, emp_picture);
			
			// 送密碼
			MailService mailService = new MailService();
			String messageText = mailService.passwordMessageText(emp_name, emp_password);
			mailService.sendMail(emp_email, "密碼通知", messageText);
			
			output.put("success", "Y");
		} else if("update".equals(action)){
			String emp_id = req.getParameter("emp_id");
			String dep_no = req.getParameter("dep_no");
			String emp_name = new String(req.getParameter("emp_name").getBytes("iso-8859-1"), "UTF-8");
			String emp_phone = req.getParameter("emp_phone");
			String emp_email = req.getParameter("emp_email");
//			String emp_password = req.getParameter("emp_password");
			Integer emp_status = Integer.valueOf(req.getParameter("emp_status"));
			byte[] emp_picture = null;
			Part part = null;
			
			try {
				// 圖片處理
				part = req.getPart("emp_picture");
				if (part != null) {
					emp_picture = InputStreamUtils.InputStreamTOByte(part);
				} else {
					emp_picture = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			EmployeeService employeeSvc = new EmployeeService();
			if (part == null || part.getSize() == 0 || part.getSubmittedFileName() == null) {
				employeeSvc.updateEmpWithoutPic(emp_id, dep_no, emp_name, emp_phone, 
						emp_email, emp_status);
			} else {
				employeeSvc.updateEmp(emp_id, dep_no, emp_name, emp_phone, emp_email, 
						 emp_status, emp_picture);
			}
			
			

			
//			for(int i = 0; i < fakeData.length(); i++){
//				JSONObject obj = (JSONObject) fakeData.get(i);
//				if((Integer) obj.get("id") == Integer.parseInt(id)){
//					JSONObject newObj = new JSONObject();
//					newObj.put("id", Integer.parseInt(id));
//					newObj.put("name", name);
//					newObj.put("mailAddress", email);
//					newObj.put("description", description);
//					fakeData.put(i, newObj);
//					break;
//				}
//			}
			output.put("success", "Y");
		} else if("delete".equals(action)){
			String[] delArray = req.getParameterValues("delArray[]");
			
			EmployeeService employeeSvc = new EmployeeService();
			for(int i = 0; i < delArray.length; i++){
				String emp_id = delArray[i];
				employeeSvc.updateResign(emp_id);
			}
			
//			ArrayList<Integer> list = new ArrayList<Integer>();
//			for(int i = 0; i < delArray.length; i++){
//				list.add(Integer.parseInt(delArray[i]));
//			}
//			for(int i = 0; i< fakeData.length(); i++){
//				JSONObject obj = (JSONObject) fakeData.get(i);
//				if(list.contains(obj.get("id"))){
//					fakeData.remove(i);
//				}
//			}
		} else if ("empSignin".equals(action)) {
			
			try {
				String emp_email = req.getParameter("emp_email");
				String user_password = req.getParameter("emp_password");
				System.out.println(emp_email);
				System.out.println(user_password);
				
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmp_id(emp_email);
				employeeVO.setEmp_password(user_password);
				
				EmployeeService employeeSvc = new EmployeeService();
				if (employeeSvc.empLogin(emp_email, user_password)) {
					EmployeeVO dbEmployeeVO = employeeSvc.getOneEmpByEmail(emp_email);
					req.getSession().setAttribute("emp_id", dbEmployeeVO.getEmp_id());
					output.put("success", "Y");
				} else {
					throw new RuntimeException();
				}
			} catch (RuntimeException re) {
				throw new RuntimeException("password don't match" + re.getMessage());
			}
		} else if ("empSignOut".equals(action)) {
			req.getSession().removeAttribute("emp_id");
			
			System.out.println("安安我要登出囉");
			String url = req.getContextPath()+"/back-end/homepage/empSignin.jsp";
			res.sendRedirect(url);
			return;
			
		} else if ("emailCheck".equals(action)) {
			String emp_email = req.getParameter("emp_email");
			System.out.println("emp_email : "+emp_email);
			
			EmployeeService employeeSvc = new EmployeeService();
			if (!employeeSvc.emailCheck(emp_email)) {
				output.put("success", true);
			} else {
				output.put("success", false);
			}
			
		}
		
		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.write(output.toString());
		out.flush();
		out.close();
	}



}
