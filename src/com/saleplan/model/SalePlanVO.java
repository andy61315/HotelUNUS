package com.saleplan.model;

import java.io.Serializable;
import java.sql.Date;




public class SalePlanVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sapl_no;
	private String sapl_name;
	private String detail;
	private Date start_date;
	private Date end_date;
	private Double sapl_discount;
	private Integer status;

	public SalePlanVO() {
		super();
	}

	public SalePlanVO(String sapl_no, String sapl_name, String detail, Date start_date, Date end_date,
			Double sapl_discount, Integer status) {
		super();
		this.sapl_no = sapl_no;
		this.sapl_name = sapl_name;
		this.detail = detail;
		this.start_date = start_date;
		this.end_date = end_date;
		this.sapl_discount = sapl_discount;
		this.status = status;
	}

	public String getSapl_no() {
		return sapl_no;
	}

	public void setSapl_no(String sapl_no) {
		this.sapl_no = sapl_no;
	}

	public String getSapl_name() {
		return sapl_name;
	}

	public void setSapl_name(String sapl_name) {
		this.sapl_name = sapl_name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Double getSapl_discount() {
		return sapl_discount;
	}

	public void setSapl_discount(Double sapl_discount) {
		this.sapl_discount = sapl_discount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SaleplanVO [sapl_no=" + sapl_no + ", sapl_name=" + sapl_name + ", detail=" + detail + ", start_date="
				+ start_date + ", end_date=" + end_date + ", sapl_discount=" + sapl_discount + ", status=" + status
				+ "]";
	}

}
