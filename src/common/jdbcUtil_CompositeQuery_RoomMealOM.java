package common;

import java.util.*;

public class jdbcUtil_CompositeQuery_RoomMealOM {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("ro_order_status".equals(columnName))
			aCondition = columnName + "=" + value;
		else if ("room_meal_order_no".equals(columnName) || "b_order_no".equals(columnName))
			aCondition = columnName + " like '%" + value + "%'";
		else if ("hiredate".equals(columnName))
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key) && !"requestURL".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String[] args) {
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("room_meal_order_no", new String[] { "" });
		map.put("b_order_no", new String[] { "" });
		map.put("ro_order_status", new String[] { "0" });
		
		String finalSQL = "select * from room_meal_order_master "
		          + jdbcUtil_CompositeQuery_RoomMealOM.get_WhereCondition(map)
		          + "order by room_meal_order_no";
		
		System.out.println(finalSQL);
	}
	
}
