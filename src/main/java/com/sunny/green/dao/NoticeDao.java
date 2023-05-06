package com.sunny.green.dao;

import com.sunny.green.vo.NoticeVo;
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

    public int deleteBoard(int notice_num);
}