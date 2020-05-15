package com.diaryreport.model;

import java.util.List;

import com.diaryreport.model.DiaryReportDAO;
import com.diaryreport.model.DiaryReportDAO_interface;
import com.diaryreport.model.DiaryReportVO;

public class DiaryReportService {
	private DiaryReportDAO_interface dao;

	public DiaryReportService() {
		dao = new DiaryReportDAO();
	}

	public DiaryReportVO addDiaryReport(String cus_id, String diary_no, Integer report_project) {

		DiaryReportVO diaryReportVO = new DiaryReportVO();

		diaryReportVO.setCus_id(cus_id);
		diaryReportVO.setDiary_no(diary_no);
		diaryReportVO.setReport_project(report_project);
//		diaryReportVO.setReport_date(report_date);
		diaryReportVO.setDiary_report_status(1);
		dao.insert(diaryReportVO);

		return diaryReportVO;
	}

	public DiaryReportVO updateDiaryReport(String diary_report_no, Integer diary_report_status)  {

		DiaryReportVO diaryReportVO = new DiaryReportVO();

		diaryReportVO.setDiary_report_no(diary_report_no);
//		diaryReportVO.setCus_id(cus_id);
//		diaryReportVO.setDiary_no(diary_no);
//		diaryReportVO.setReport_project(report_project);
//		diaryReportVO.setReport_date(report_date);
		diaryReportVO.setDiary_report_status(diary_report_status);
		dao.update(diaryReportVO);

		return dao.findByPrimaryKey(diary_report_no);
	}

//	public void deleteDiaryReport(String diary_report_no) {
//		dao.delete(diary_report_no);
//	}

	public DiaryReportVO getOneDiaryReport(String diary_report_no) {
		return dao.findByPrimaryKey(diary_report_no);
	}

	public List<DiaryReportVO> getAll() {
		return dao.getAll();
	}
}
