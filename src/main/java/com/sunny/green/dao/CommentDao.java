package com.sunny.green.dao;

import com.sunny.green.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommentDao {
    public int insertComment(CommentVo commentVo);
    List<CommentVo> selectAllComment();
    public int getCommentTotal(int com_num);
    public int updateComment(CommentVo commentVo);
    public int deleteComment(int com_num);

}