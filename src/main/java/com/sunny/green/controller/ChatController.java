package com.sunny.green.controller;

import com.sunny.green.dao.UserDao;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final UserDao ud;

    @GetMapping("/chat")
    public String chat(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("alert", "로그인이 필요한 페이지입니다.");
            model.addAttribute("url", "/login");
        } else {
            UserVo userDB = (UserVo) session.getAttribute("user");
            UserVo user = ud.selectAll1(userDB.getUser_name());
            model.addAttribute("user", user);

            return "chat/chat";
        }
        return "alert";
    }
}
