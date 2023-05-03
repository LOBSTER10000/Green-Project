package com.sunny.green.controller;

import com.sunny.green.dao.ExchangeDao;
import com.sunny.green.dao.UserDao;
import com.sunny.green.vo.ProductWithImgVo;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {
    private final ExchangeDao ed;
    private final UserDao ud;

    @GetMapping("/")
    public String index(Model mo) {
        List<ProductWithImgVo> pv = ed.selectProductAll();
        mo.addAttribute("pv", pv);

        return "index";
    }

    @GetMapping("/index")
    public String index1(Model mo) {
        List<ProductWithImgVo> pv = ed.selectProductAll();
        mo.addAttribute("pv", pv);
        return "/index";
    }


    @GetMapping("/myWrite")
    public String myWrite(Model model, HttpSession session){
        UserVo uservo = (UserVo) session.getAttribute("user");
        model.addAttribute("user", uservo);

        return "/myPage/myWrite";
    }

    @GetMapping("/myComment")
    public String myComment(Model model, HttpSession session){
        UserVo uservo = (UserVo) session.getAttribute("user");
        model.addAttribute("user", uservo);
        return "/myPage/myComment";
    }

    

}
