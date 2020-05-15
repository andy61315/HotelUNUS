package com.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

/**
 * Servlet implementation class MapStatusOnStart
 */
public class MapStatusOnStart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapStatusOnStart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		ServletContext context = getServletContext();
		// TODO Auto-generated method stub
		super.init();
		
		//會員認證狀態
		Map<Integer, String> cus_Ck = new HashMap<>();
		cus_Ck.put(0, "未認證會員");
		cus_Ck.put(1, "已認證會員");
		cus_Ck.put(2, "已註銷會員");
		context.setAttribute( "cus_Ck",cus_Ck );
		//國內外判別
	    Map<Integer,String> country = new HashMap<>();
	    country.put(0, "國內");
	    country.put(1, "國外");
		context.setAttribute("country" ,country );
	    
	    
		//會員通知狀態
		Map<Integer,String> message_Status = new HashMap<>();
		message_Status.put(0, "未讀" );
		message_Status.put(1, "已讀");
		context.setAttribute("message_Status" ,message_Status );
		
		
		//公告狀態
		Map<Integer,String> news_Status = new HashMap<>();
		news_Status.put(0, "未公告" );
		news_Status.put(1, "已公告");
		context.setAttribute( "news_Status",news_Status );
		  
		  
		//訂房訂單狀態
		Map<Integer,String> bomStatus = new HashMap<>();
		//map的Key是表格內的狀態數字(所謂的工程師畫面)，Value是要取出來給使用者看的文字
		bomStatus.put(0,"進行中(未入住)");
		bomStatus.put(1,"進行中(已入住)");
		bomStatus.put(2,"訂單完成");
		bomStatus.put(3,"訂單取消");
		context.setAttribute("bomStatus" ,bomStatus );
		
		//房型狀態
		Map<Integer,String> roomType_Status = new HashMap<>();
		roomType_Status.put(0, "下架" );
		roomType_Status.put(1, "上架" );
		context.setAttribute("roomType_Status" ,roomType_Status );
				
		// 員工狀態
	    Map<Integer, String> emp_status = new HashMap<Integer, String>();
	    emp_status.put(0, "離職");
	    emp_status.put(1, "在職");
	    context.setAttribute("emp_status", emp_status);
				
		// 餐點狀態
		Map<Integer, String> meal_status = new HashMap<Integer, String>();
		meal_status.put(0, "下架");
		meal_status.put(1, "上架");
		context.setAttribute("meal_status" ,meal_status );
		
		// 訂餐訂單狀態
		Map<Integer, String> ro_order_status = new HashMap<Integer, String>();
		ro_order_status.put(0, "備餐中");
		ro_order_status.put(1, "出餐中");
		ro_order_status.put(2, "已出餐(未收款)");
		ro_order_status.put(3, "已完成(已收款)");
		ro_order_status.put(4, "訂餐取消");
		context.setAttribute("ro_order_status" ,ro_order_status );
		
		
		//預約狀態
		Map<Integer,String> resvStatus = new HashMap<>();

		resvStatus.put(0,"取消");
		resvStatus.put(1,"正常");
		resvStatus.put(2,"已入座");
		context.setAttribute("resvStatus" ,resvStatus );



		//餐廳狀態
		Map<Integer,String> restaurantStatus = new HashMap<>();

		restaurantStatus.put(0,"已休業");
		restaurantStatus.put(1,"營業中");
		context.setAttribute( "restaurantStatus", restaurantStatus);


		//預約時段
		Map<Integer,String> resreservation = new HashMap<>();
		//中午時段
		resreservation.put(0,"11:00");
		resreservation.put(1,"11:30");
		resreservation.put(2,"12:00");
		resreservation.put(3,"12:30");
		resreservation.put(4,"13:00");
		resreservation.put(5,"13:30");
		resreservation.put(6,"14:00");
		//晚上時段
		resreservation.put(7,"17:00");
		resreservation.put(8,"17:30");
		resreservation.put(9,"18:00");
		resreservation.put(10,"18:30");
		resreservation.put(11,"19:00");
		resreservation.put(12,"19:30");
		resreservation.put(13,"20:00");
		context.setAttribute("resreservation" , resreservation );


		//房間狀態
		Map<Integer,String> roomStatus = new HashMap<>();
		roomStatus.put(0, "空房" );
		roomStatus.put(1, "有房客");
		roomStatus.put(2, "已排房");
		roomStatus.put(3, "將離館/已排房");
		roomStatus.put(4, "將離館");
		roomStatus.put(5, "不再使用");
		context.setAttribute("roomStatus" ,roomStatus );
		
		//清掃狀態
		Map<Integer,String> cleanStatus = new HashMap<>();
		cleanStatus.put(0, "故障" );
		cleanStatus.put(1, "乾淨");
		cleanStatus.put(2, "未打掃");
		context.setAttribute("cleanStatus" ,cleanStatus );
		
		//優惠狀態
		Map<Integer,String> saplStatus = new HashMap<>();
		saplStatus.put(0, "下架" );
		saplStatus.put(1, "上架");
		saplStatus.put(2, "待審核");
		context.setAttribute("saplStatus" ,saplStatus );
				
				
				
				
		//訂單明細備餐狀態
		Map<Integer,String> resMODCookingStatus = new HashMap<>();

		resMODCookingStatus.put(0,"餐點製作中");
		resMODCookingStatus.put(1,"已出餐");
		context.setAttribute("resMODCookingStatus" ,resMODCookingStatus );

		//訂單明細出餐狀態
		Map<Integer,String> resMODServedStatus = new HashMap<>();

		resMODServedStatus.put(0,"未送餐");
		resMODServedStatus.put(1,"已送餐");
		context.setAttribute("resMODServedStatus" ,resMODServedStatus );

		//訂單明細狀態
		Map<Integer,String> resMODCCanceledStatus = new HashMap<>();

		resMODCCanceledStatus.put(0,"訂單正常");
		resMODCCanceledStatus.put(1,"訂單取消");
		context.setAttribute("resMODCCanceledStatus" ,resMODCCanceledStatus );

		//訂單狀態
		Map<Integer,String> resMOMStatus = new HashMap<>();

		resMOMStatus.put(1,"未付款");
		resMOMStatus.put(2,"已付款");
		resMOMStatus.put(3,"訂單取消");
		context.setAttribute("resMOMStatus" ,resMOMStatus );
		
		
		
		//日誌狀態
		Map<Integer,String> diaryStatus = new HashMap<>();
		diaryStatus.put(0, "下架" );
		diaryStatus.put(1, "上架");
		context.setAttribute("diaryStatus" , diaryStatus);


		//留言狀態
		Map<Integer,String> diaryMessageStatus = new HashMap<>();
		diaryMessageStatus.put(0, "下架" );
		diaryMessageStatus.put(1, "上架");
		context.setAttribute("diaryMessageStatus" , diaryMessageStatus);

		//留言檢舉狀態
		Map<Integer,String> messageReportStatus = new HashMap<>();
		messageReportStatus.put(0, "處理" );
		messageReportStatus.put(1, "未處理");
		context.setAttribute("messageReportStatus" , messageReportStatus);

		//留言檢舉項目
		Map<Integer,String> messageReportProject = new HashMap<>();
		messageReportProject.put(0, "與日誌無關" );
		messageReportProject.put(1, "不雅文字");
		messageReportProject.put(2, "人身攻擊");
		context.setAttribute("messageReportProject" , messageReportProject);
		
		//日誌檢舉項目
		Map<Integer,String> diaryReportProject = new HashMap<>();
		diaryReportProject.put(0, "與飯店無關" );
		diaryReportProject.put(1, "不雅文字");
		diaryReportProject.put(2, "人身攻擊");
		context.setAttribute("diaryReportProject" ,diaryReportProject );
		
		//日誌檢舉狀態
		Map<Integer,String> diaryReportStatus = new HashMap<>();
		diaryReportStatus.put(0, "處理" );
		diaryReportStatus.put(1, "未處理");
		context.setAttribute("diaryReportStatus" ,diaryReportStatus );
		
		getDifCapRTToFront(getServletContext());
	}
	
	public void getDifCapRTToFront(ServletContext context) {//直接在更改房型狀態/新增房型時把可供選擇的房型記錄下來
//		ServletContext context = getServletContext();
		RoomTypeService rtSvc = new RoomTypeService();
		Map<String,String[]> map = new HashMap<>();
		map.put("ROOM_TYPE_STATUS",new String[]{"1"});
		List<RoomTypeVO> testList = null;
		int i = 1;
		while(i < 7) {
			map.put("PERSON_CAPACITY",new String[] {String.valueOf(i)});
			testList = rtSvc.getAllBy(map);
			context.setAttribute("rtList_" + i ,testList );
			i++;
		}
	}
}


