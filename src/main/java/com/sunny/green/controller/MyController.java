package com.sunny.green.controller;

import com.sunny.green.dao.*;
import com.sunny.green.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MyController {

    private final ExchangeDao ed;

    private final UserDao ud;
    private final ProfileImgDao pid;

    private final NoticeDao nd;
    private final BbsDao bd;
    private final CommentDao cd;

    @GetMapping("/")
    public String index(Model mo) {
        List<ProductWithImgVo> pv = ed.selectProductAll();
        List<NoticeVo> nv = nd.selectMainNotice();
        mo.addAttribute("pv", pv);
        mo.addAttribute("nv", nv);

        return "index";
    }

    @GetMapping("/index")
    public String index1(Model mo) {
        List<ProductWithImgVo> pv = ed.selectProductAll();
        List<NoticeVo> nv = nd.selectMainNotice();
        mo.addAttribute("pv", pv);
        mo.addAttribute("nv", nv);
        return "index";
    }


    @GetMapping("/myWrite")
    public String myWrite(Model model, HttpSession session, PageVo search, @RequestParam(required = false) String searchType, @RequestParam(required = false) String searchValue) throws Exception{
        UserVo uservo = (UserVo) session.getAttribute("user");
        model.addAttribute("user", uservo);
        ProfileImgVo profileImgVo = pid.selectProfileImg(uservo.getUser_id());
        model.addAttribute("profileImgVo", profileImgVo);
        List<BbsVo> bv;
        if (searchType == null || searchValue == null) {
            bv = bd.selectAllBoard2(uservo.getUser_id());
            log.info(bv);
        } else {
            bv = bd.searchBoard(search, searchType, searchValue);
            log.info(bv);
        }
        model.addAttribute("bv", bv);
        return "myPage/myWrite";
    }

    @PostMapping("/pagination_myWrite")
    @ResponseBody
    public List<BbsVo> getNoticeInfo(HttpSession session, PageVo search, @RequestParam(required = false) String searchType, @RequestParam(required = false) String searchValue) {
        UserVo uservo = (UserVo) session.getAttribute("user");
        List<BbsVo> bbsVos;
        if (searchType == null || searchValue == null) {
            bbsVos = bd.selectAllBoard2(uservo.getUser_id());

        } else {
            bbsVos = bd.searchBoard(search, searchType, searchValue);
            log.info(bbsVos);
        }
        log.info(">>>>>>>>>>>>>"+bbsVos);
        return bbsVos;
    }

    @GetMapping("/myComment")
    public String myComment(Model model, HttpSession session, PageVo search, @RequestParam(required = false) String searchType, @RequestParam(required = false) String searchValue) throws Exception{
        UserVo uservo = (UserVo) session.getAttribute("user");
        model.addAttribute("user", uservo);
        ProfileImgVo profileImgVo = pid.selectProfileImg(uservo.getUser_id());
        model.addAttribute("profileImgVo", profileImgVo);
        List<CommentVo> cv;
        if (searchType == null || searchValue == null) {
            cv = cd.selectAllComments(uservo.getUser_id());
            log.info(cv);
        } else {
            cv = cd.searchComment(search, searchType, searchValue);
            log.info(cv);
        }
        model.addAttribute("cv", cv);
        return "myPage/myComment";
    }

    @PostMapping("/pagination_myComment")
    @ResponseBody
    public List<CommentVo> getCommentInfo(HttpSession session, PageVo search, @RequestParam(required = false) String searchType, @RequestParam(required = false) String searchValue) {
        UserVo uservo = (UserVo) session.getAttribute("user");
        List<CommentVo> cv;
        if (searchType == null || searchValue == null) {
            cv = cd.selectAllComments(uservo.getUser_id());
        } else {
            cv = cd.searchComment(search, searchType, searchValue);

        }
        log.info(">>>>>>>>>>>>>"+cv);
        return cv;
    }

}
