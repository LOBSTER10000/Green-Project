package com.sunny.green.controller;

import com.sunny.green.dao.CommentDao;
import com.sunny.green.vo.CommentVo;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@Log4j2
public class CommentController {

    private final CommentDao cd;


    // 댓글 입력

    @PostMapping("insertComment.do")
    @ResponseBody
    public void postComment(CommentVo commentVo, HttpSession session) {
        System.out.println("commentVo1>>>>>" + commentVo);
//        cd.updateComNum();



        if (session.getAttribute("user") != null) {
            UserVo user = (UserVo) session.getAttribute("user");
            String userId = user.getUser_id();
            commentVo.setUser_id(userId);
        }
        System.out.println("commentVo2>>>>>" + commentVo);
        int insertResult = cd.insertComment(commentVo);
    }

    // 댓글 수정
    @PostMapping("/updateComment.do")
    @ResponseBody
    public void updateComment(CommentVo commentVo) {
        log.info("댓글값: " + commentVo.getCom_num());

        int updateResult = cd.updateComment(commentVo);

        if (updateResult > 0) {
            log.info("댓글이 수정되었습니다.");
        } else {
            log.info("댓글 수정에 실패했습니다.");
        }
    }

    // 댓글 삭제
    @GetMapping("deleteComment.do")
    @ResponseBody
    public void deleteComment(int com_num) {
        int str = cd.deleteComment(com_num);
        System.out.println("댓글 삭제>>>>>"+str);
        log.info("정보값 " + com_num);
//        int str1 = cd.updateComNum();

    }

}