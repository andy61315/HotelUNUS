package com.cus.model;

import java.util.Arrays;
import java.sql.Date;

public class CustomerVO implements java.io.Serializable{
	String cus_Id,cus_Name,cus_Email;
	Integer country,cus_Ck;
	String cus_Cel,id_Num,cus_Password,captcha;
	byte[] idf_Pic;
	java.sql.Date cus_Bir, reg_Date;
	
	public CustomerVO(){
		super();
	}
	
	public CustomerVO(String cus_Id, String cus_Name, String cus_Email, String cus_Cel, Integer country, String id_Num, java.sql.Date cus_Bir, String cus_Password,String captcha, java.sql.Date reg_Date, byte[] idf_Pic, Integer cus_Ck) {
	super();
	this.cus_Id = cus_Id;
	this.cus_Name = cus_Name;
	this.cus_Email = cus_Email;
	this.cus_Cel = cus_Cel;
	this.country = country;
	this.id_Num = id_Num;
	this.cus_Bir = cus_Bir;
	this.cus_Password = cus_Password;
	this.captcha = captcha;
	this.reg_Date = reg_Date;
	this.idf_Pic = idf_Pic;
	this.cus_Ck = cus_Ck;
	}

	public String getCus_Id() {
		return cus_Id;
	}

	public void setCus_Id(String cus_Id) {
		this.cus_Id = cus_Id;
	}

	public String getCus_Name() {
		return cus_Name;
	}

	public void setCus_Name(String cus_Name) {
		this.cus_Name = cus_Name;
	}

	public String getCus_Email() {
		return cus_Email;
	}

	public void setCus_Email(String cus_Email) {
		this.cus_Email = cus_Email;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getCus_Ck() {
		return cus_Ck;
	}

	public void setCus_Ck(Integer cus_Ck) {
		this.cus_Ck = cus_Ck;
	}

	public String getCus_Cel() {
		return cus_Cel;
	}

	public void setCus_Cel(String cus_Cel) {
		this.cus_Cel = cus_Cel;
	}

	public String getId_Num() {
		return id_Num;
	}

	public void setId_Num(String id_Num) {
		this.id_Num = id_Num;
	}

	public String getCus_Password() {
		return cus_Password;
	}

	public void setCus_Password(String cus_Password) {
		this.cus_Password = cus_Password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public byte[] getIdf_Pic() {
		return idf_Pic;
	}

	public void setIdf_Pic(byte[] idf_Pic) {
		this.idf_Pic = idf_Pic;
	}

	public java.sql.Date getCus_Bir() {
		return cus_Bir;
	}

	public void setCus_Bir(java.sql.Date cus_Bir) {
		this.cus_Bir = cus_Bir;
	}

	public java.sql.Date getReg_Date() {
		return reg_Date;
	}

	public void setReg_Date(java.sql.Date reg_Date) {
		this.reg_Date = reg_Date;
	}

	@Override
	public String toString() {
		return "CustomerVO [cus_Id=" + cus_Id + ", cus_Name=" + cus_Name + ", cus_Email=" + cus_Email + ", country="
				+ country + ", cus_Ck=" + cus_Ck + ", cus_Cel=" + cus_Cel + ", id_Num=" + id_Num + ", cus_Password="
				+ cus_Password + ", captcha=" + captcha + ", idf_Pic=" + Arrays.toString(idf_Pic) + ", cus_Bir="
				+ cus_Bir + ", reg_Date=" + reg_Date + "]";
	}
	



	

}
