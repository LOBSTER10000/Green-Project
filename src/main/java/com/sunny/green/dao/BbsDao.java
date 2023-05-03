package com.sunny.green.dao;

import com.sunny.green.vo.AdminVo;
import com.sunny.green.vo.BbsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BbsDao {

    public int insertBoard(BbsVo bbsVo);

    public List<BbsVo> selectBoardAll();

    public BbsVo selectBoard(int boardNum);

    public int updateBoard(BbsVo bbs);

    public int deleteBoard(int board_num);

    public int updateBoardNum();
}