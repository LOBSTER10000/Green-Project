package com.sunny.green.dao;

import com.sunny.green.vo.MailVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailDao {
    public int insertMail(MailVo mailVo);
}
