package com.sunny.green.service;

import com.sunny.green.vo.UserVo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface UserService {

    public UserVo kakaoUser(String code);
}
