package com.sunny.green.dao;

import com.sunny.green.vo.PageVo;
import com.sunny.green.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    public int joinUser(UserVo user);

    public UserVo selectUser(UserVo user);

    public UserVo selectUserId(String userId);

    public UserVo selectAll1(String userId);

    public int updateUser(UserVo user);

    public int deleteId(String userId);

    public int updatePoint(UserVo vo);

    //보영
    public List<UserVo> selectAll();

    List<UserVo> selectAll2(PageVo search, String searchType, String keyword);

/*   public List<UserVo> selectAll2(PageVo pageVo);*/

}
