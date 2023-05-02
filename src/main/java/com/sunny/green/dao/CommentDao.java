package com.sunny.green.dao;

import com.sunny.green.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CommentDao {
    public int insertComment(CommentVo commentVo);
    public List<CommentVo> selectAllComment();
    public int getCommentTotal(int com_num);
    public int updateComment(CommentVo commentVo);
    public int deleteComment(int com_num);

}