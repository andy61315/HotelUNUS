package test;

public class PrintSQL {
	public static void main(String[] args) {
//		110 115
		String[] rtName = {"RT004","RT005","RT006","RT007","RT008","RT009","RT010","RT011","RT012","RT013","RT014"};
		int[] qty = {8,10,15,20,20,12,15,5,10,10,15};
		int rtIdx = 201;
		for(int i = 0 ; i < rtName.length; i++) {
			for(int j = 0; j < qty[i] ;j++) {
				System.out.println("INSERT INTO ROOM ( room_id, room_type_no, room_no) VALUES "
							+ "('RM'||TO_CHAR(SEQ_ROOM_ID.NEXTVAL,'FM000'),'" + rtName[i] + "','"+ rtIdx+"');");
				rtIdx++;
				if(rtIdx%100 > 20)rtIdx = rtIdx +80;//每層只能有20間房
			}
		}
	}
}
