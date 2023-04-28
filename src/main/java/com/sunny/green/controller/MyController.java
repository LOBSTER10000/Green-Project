package com.sunny.green.controller;

import com.sunny.green.dao.ExchangeDao;
import com.sunny.green.dao.ProfileImgDao;
import com.sunny.green.dao.UserDao;
import com.sunny.green.vo.ProductWithImgVo;
import com.sunny.green.vo.ProfileImgVo;
import com.sunny.green.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private ExchangeDao ed;

    @Autowired
    private UserDao ud;

    @Autowired
    private ProfileImgDao pid;

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
        return "index";
    }


    @GetMapping("/myWrite")
    public String myWrite(Model model, HttpSession session){
        UserVo uservo = (UserVo) session.getAttribute("user");
        model.addAttribute("user", uservo);
        ProfileImgVo profileImgVo = pid.selectProfileImg(uservo.getUser_id());
        model.addAttribute("profileImgVo", profileImgVo);
        return "myPage/myWrite";
    }

    @GetMapping("/myComment")
    public String myComment(Model model, HttpSession session){
        UserVo uservo = (UserVo) session.getAttribute("user");
        model.addAttribute("user", uservo);
        ProfileImgVo profileImgVo = pid.selectProfileImg(uservo.getUser_id());
        model.addAttribute("profileImgVo", profileImgVo);
        return "myPage/myComment";
    }

    

}
