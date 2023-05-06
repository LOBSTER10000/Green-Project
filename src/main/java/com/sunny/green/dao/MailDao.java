package com.sunny.green.dao;

import com.sunny.green.vo.MailVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MailDao {
    public int insertMail(MailVo mailVo);
}
