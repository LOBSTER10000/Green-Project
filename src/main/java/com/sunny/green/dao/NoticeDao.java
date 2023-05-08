package com.sunny.green.dao;

import com.sunny.green.vo.NoticeVo;
import com.sunny.green.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeDao {

    public int insertNotice(NoticeVo noticeVo);

    public List<NoticeVo> selectNoticeAll();

    public NoticeVo selectNotice(int noticeNum);

    public int updateNotice(NoticeVo notice);

    public int deleteNotice(int notice_num);

    public int updateNoticeNum();

    public List<NoticeVo> selectMainNotice();

    public List<NoticeVo> selectAllNotice();

    public List<NoticeVo> searchNotice(PageVo search, String searchType, String searchValue);

    public List<NoticeVo> myPageNotice(String user_Id);
}