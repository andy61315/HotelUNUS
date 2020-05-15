package com.diaryreport.model;

import java.sql.Date;

public class DiaryReportVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String diary_report_no;
	private String cus_id;
	private String diary_no;
	private Integer report_project;
	private Date report_date;
	private Integer diary_report_status;
	
	public DiaryReportVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiaryReportVO(String diary_report_no, String cus_id, String diary_no, Integer report_project,
			Date report_date, Integer diary_report_status) {
		super();
		this.diary_report_no = diary_report_no;
		this.cus_id = cus_id;
		this.diary_no = diary_no;
		this.report_project = report_project;
		this.report_date = report_date;
		this.diary_report_status = diary_report_status;
	}

	public String getDiary_report_no() {
		return diary_report_no;
	}

	public void setDiary_report_no(String diary_report_no) {
		this.diary_report_no = diary_report_no;
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

	public Integer getReport_project() {
		return report_project;
	}

	public void setReport_project(Integer report_project) {
		this.report_project = report_project;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public Integer getDiary_report_status() {
		return diary_report_status;
	}

	public void setDiary_report_status(Integer diary_report_status) {
		this.diary_report_status = diary_report_status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DiaryReportVO [diary_report_no=" + diary_report_no + ", cus_id=" + cus_id + ", diary_no=" + diary_no
				+ ", report_project=" + report_project + ", report_date=" + report_date + ", diary_report_status="
				+ diary_report_status + "]";
	}
	
	
}
