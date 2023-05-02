package com.sunny.green.controller;

import com.sunny.green.dao.CommentDao;
import com.sunny.green.vo.CommentVo;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class CommentController {


    @Autowired
    private CommentDao cd;

    public CommentController(CommentDao cd) {
        this.cd = cd;
    }

//    @PostMapping("/board/comment/write")
//    private String insertComment(@RequestParam("com_num")int com_num,
//                                 @RequestParam("com_content") String com_content) throws Exception{
//        CommentVo commentVo = new CommentVo();
//        commentVo.setCom_content(com_content);
//        commentVo.setCom_num(com_num);
//        cd.insertComment(commentVo);
//        String redirect_url = "redirect:/boardDetail?board_num="+Integer.toString(com_num);
//        return redirect_url;
//    }
//
//    @GetMapping("/selectAllComment")
//    private int selectAllComment(@RequestParam("com_num") int com_num, Model model, CommentVo commentVo) throws Exception{
//        CommentVo com = new CommentVo();
//        com.setCom_num(com_num);
//        model.addAttribute("com_num", com);
//        return cd.insertComment(commentVo);
//    }
//---------------

    @GetMapping("/selectAllComment")
    public String boardDetail(CommentVo commentVo, Model model, HttpSession session) {
        CommentVo com = (CommentVo) session.getAttribute("com_num");
        model.addAttribute("com_num", com);

    @GetMapping("/loadCommentList")
    public String selectAllComment(CommentVo commentVo, UserVo user, Model model, HttpSession session) {
        CommentVo com = (CommentVo) session.getAttribute("comment");
        model.addAttribute("comment", commentVo);
        model.addAttribute("user", user);
        List<CommentVo> bbs = cd.selectAllComment();
        UserVo userVo = (UserVo) session.getAttribute("user");
        return "bbs/boardDetail";

    }

    @PostMapping("insertComment.do")
    public void postComment(CommentVo commentVo, HttpSession session)  {
        System.out.println("commentVo1>>>>>"+commentVo);

        if (session.getAttribute("user") != null) {
            UserVo user = (UserVo) session.getAttribute("user");
            String userId = user.getUser_id();
            commentVo.setUser_id(userId);
        }
        System.out.println("commentVo2>>>>>"+commentVo);
        int insertResult = cd.insertComment(commentVo);
//        String redirect_url = "redirect:/boardDetail?board_num="+Integer.toString(com_num);
//        return "bbs/boardDetail";
    }

}
