package com.diarymessage.model;

import java.util.List;
import java.util.Set;

public interface DiaryMessageDAO_interface {
	public int insert(DiaryMessageVO diaryMessageVO);
    public int update(DiaryMessageVO diaryMessageVO);
    public int delete(String diaryMessage_no);
    public DiaryMessageVO findByPrimaryKey(String diaryMessage_no);
    public List<DiaryMessageVO> getAll();
  //查詢某日誌的留言(一對多)(回傳 Set)
    public List<DiaryMessageVO> getMsgByDiaryno(String diary_no);
}
