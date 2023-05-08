package com.sunny.green.controller;

import com.sunny.green.dao.CommentDao;
import com.sunny.green.vo.CommentVo;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentDao cd;

    @GetMapping("/loadCommentList")
    public String selectAllComment(CommentVo commentVo, UserVo user, Model model, HttpSession session) {
        CommentVo com = (CommentVo) session.getAttribute("comment");
        model.addAttribute("comment", commentVo);
        model.addAttribute("user", user);
        List<CommentVo> comment = cd.selectAllComment();
        UserVo userVo = (UserVo) session.getAttribute("user");
        return "bbs/boardDetail";

    }

    @PostMapping("insertComment.do")
    public String postComment(CommentVo commentVo, HttpSession session, int board_num)  {
        System.out.println("commentVo1>>>>>"+commentVo);

        if (session.getAttribute("user") != null) {
            UserVo user = (UserVo) session.getAttribute("user");
            String userId = user.getUser_id();
            commentVo.setUser_id(userId);
        }
        System.out.println("commentVo2>>>>>"+commentVo);
        int insertResult = cd.insertComment(commentVo);
//        String redirect_url = "redirect:/boardDetail?board_num="+Integer.toString(com_num);
        return "bbs/boardDetail";
//            return "redirect:/boardDetail/" + board_num;

    }

}