package com.sunny.green.controller;

import com.sunny.green.dao.AdminDao;
import com.sunny.green.dao.BbsDao;
import com.sunny.green.vo.AdminVo;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final AdminDao ad;
    @GetMapping("/notice")
    public String index(Model model, HttpSession session, UserVo uservo) {
        uservo = (UserVo) session.getAttribute("user");
        return "bbs/noticeList";
    }
}
