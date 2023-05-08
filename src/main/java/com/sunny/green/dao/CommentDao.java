package com.sunny.green.dao;

import com.sunny.green.vo.CommentVo;
import com.sunny.green.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CommentDao {
    public int insertComment(CommentVo commentVo);
    public List<CommentVo> selectAllComment(int board_num);
    public int commentTotal(int board_num);
    public int updateComment(CommentVo commentVo);
    public int deleteComment(int com_num);
    public CommentVo selectComment(int com_num);
    public int deleteCommentByBoardNum(int board_num);
    public int updateComNum();

    List<CommentVo> getCommentsByBoardNum(int boardNum);

    List<CommentVo> myPageComment(String user_id);

    List<CommentVo> selectAllComments(String user_id);

    List<CommentVo> searchComment(PageVo search, String searchType, String searchValue);
}