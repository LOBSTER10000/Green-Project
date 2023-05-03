package com.sunny.green.dao;

import com.sunny.green.vo.ProImgVo;
import com.sunny.green.vo.ProfileImgVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileImgDao {

    public int insProfileImg(ProfileImgVo profileImgVo);

    public ProfileImgVo selectProfileImg(String user_id);
}
