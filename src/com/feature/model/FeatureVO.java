package com.feature.model;

import java.io.Serializable;

public class FeatureVO implements Serializable{
	private String feat_no;
	private String feat_name;
	
	public FeatureVO() {
		super();
	}
	
	public FeatureVO(String feat_no, String feat_name) {
		super();
		this.feat_no = feat_no;
		this.feat_name = feat_name;
	}
	
	public String getFeat_no() {
		return feat_no;
	}
	public void setFeat_no(String feat_no) {
		this.feat_no = feat_no;
	}
	public String getFeat_name() {
		return feat_name;
	}
	public void setFeat_name(String feat_name) {
		this.feat_name = feat_name;
	}
	
}
