package com.diarymessage.model;

import java.sql.Date;

public class DiaryMessageVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String diary_message_no;
	private String cus_id;
	private String diary_no;
	private Date diary_message_date;
	private String diary_message_content;
	private Integer diary_message_status;
	
	public DiaryMessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiaryMessageVO(String diary_message_no, String cus_id, String diary_no, Date diary_message_date,
			String diary_message_content, Integer diary_message_status) {
		super();
		this.diary_message_no = diary_message_no;
		this.cus_id = cus_id;
		this.diary_no = diary_no;
		this.diary_message_date = diary_message_date;
		this.diary_message_content = diary_message_content;
		this.diary_message_status = diary_message_status;
	}

	public String getDiary_message_no() {
		return diary_message_no;
	}

	public void setDiary_message_no(String diary_message_no) {
		this.diary_message_no = diary_message_no;
	}

	public String getCus_id() {
		return cus_id;
	}

	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}

	public String getDiary_no() {
		return diary_no;
	}

	public void setDiary_no(String diary_no) {
		this.diary_no = diary_no;
	}

	public Date getDiary_message_date() {
		return diary_message_date;
	}

	public void setDiary_message_date(Date diary_message_date) {
		this.diary_message_date = diary_message_date;
	}

	public String getDiary_message_content() {
		return diary_message_content;
	}

	public void setDiary_message_content(String diary_message_content) {
		this.diary_message_content = diary_message_content;
	}

	public Integer getDiary_message_status() {
		return diary_message_status;
	}

	public void setDiary_message_status(Integer diary_message_status) {
		this.diary_message_status = diary_message_status;
	}

	@Override
	public String toString() {
		return "DiaryMessageVO [diary_message_no=" + diary_message_no + ", cus_id=" + cus_id + ", diary_no=" + diary_no
				+ ", diary_message_date=" + diary_message_date + ", diary_message_content=" + diary_message_content
				+ ", diary_status=" + diary_message_status + "]";
	}
	
	
	
}
