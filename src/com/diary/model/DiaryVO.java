package com.diary.model;

import java.sql.Date;

public class DiaryVO implements java.io.Serializable, Comparable<DiaryVO>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String diary_no;
	private String cus_id;
	private Date diary_date;
	private String diary_title;
	private String diary_content;
	private Integer diary_status;
	
	public DiaryVO() {
		super();
	}

	public DiaryVO(String diary_no, String cus_id, Date diary_date, String diary_title, String diary_content,
			Integer diary_status) {
		super();
		this.diary_no = diary_no;
		this.cus_id = cus_id;
		this.diary_date = diary_date;
		this.diary_title = diary_title;
		this.diary_content = diary_content;
		this.diary_status = diary_status;
	}

	

	public String getDiary_no() {
		return diary_no;
	}

	public void setDiary_no(String diary_no) {
		this.diary_no = diary_no;
	}

	public String getCus_id() {
		return cus_id;
	}

	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}

	public Date getDiary_date() {
		return diary_date;
	}

	public void setDiary_date(Date diary_date) {
		this.diary_date = diary_date;
	}

	public String getDiary_title() {
		return diary_title;
	}

	public void setDiary_title(String diary_title) {
		this.diary_title = diary_title;
	}

	public String getDiary_content() {
		return diary_content;
	}

	public void setDiary_content(String diary_content) {
		this.diary_content = diary_content;
	}

	public Integer getDiary_status() {
		return diary_status;
	}

	public void setDiary_status(Integer diary_status) {
		this.diary_status = diary_status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DiaryVO [diary_no=" + diary_no + ", cus_id=" + cus_id + ", diary_date=" + diary_date + ", diary_title="
				+ diary_title + ", diary_content=" + diary_content + ", diary_status=" + diary_status + "]";
	}	
	public int compareTo(DiaryVO aDiaryVO) {
		if(this.getDiary_date().getTime() > aDiaryVO.getDiary_date().getTime()) {
			return 1;
		}else if(this.getDiary_date().getTime() < aDiaryVO.getDiary_date().getTime()) {
			return -1;
		}else {
			return 0;
		}
	}	
	
}
