package com.diaryreport.model;

import java.util.List;

public interface DiaryReportDAO_interface {
	public int insert(DiaryReportVO diaryReportVO);
    public int update(DiaryReportVO diaryReportVO);
    public int delete(String diary_report_no);
    public DiaryReportVO findByPrimaryKey(String diary_report_no);
    public List<DiaryReportVO> getAll();

}
