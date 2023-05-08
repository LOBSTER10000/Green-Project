package com.sunny.green.dao;

import com.sunny.green.vo.AdminVo;
import com.sunny.green.vo.BbsVo;
import com.sunny.green.vo.CommentVo;
import com.sunny.green.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BbsDao {

    public int insertBoard(BbsVo bbsVo);

    public List<BbsVo> selectBoardAll();

    public BbsVo selectBoard(int boardNum);

    public int updateBoard(BbsVo bbs);

    public int deleteBoard(int board_num);

    void insertComment(CommentVo commentVo);

    public int deleteBoardWithComments(int board_num);

    public int updateBoardNum();

    public List<BbsVo> selectAllBoard();
    public List<BbsVo> selectAllBoard2(String user_id);

    public List<BbsVo> searchBoard(PageVo search, String searchType, String searchValue);

    public List<BbsVo> myPageBoard(String user_id);
}