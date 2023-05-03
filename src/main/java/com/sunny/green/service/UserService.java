package com.sunny.green.service;

import com.sunny.green.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserVo kakaoUser(String code);
}
