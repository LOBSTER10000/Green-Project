package com.sunny.green.controller;

import com.sunny.green.dao.BbsDao;
import com.sunny.green.dao.CommentDao;
import com.sunny.green.dao.UserDao;
import com.sunny.green.vo.BbsVo;
import com.sunny.green.vo.CommentVo;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BbsDao bd;

    private final CommentDao cd;

    // Q&A 목록
    @GetMapping("/board")
    public String index(Model model) {
        List<BbsVo> bbs = bd.selectBoardAll();
        model.addAttribute("bbs", bbs);
        return "bbs/boardList";
    }

    // Q&A 글작성 폼
    @GetMapping("/boardPost")
    public String boardPost(UserVo user, HttpSession session, Model model) {
        UserVo userVo = (UserVo) session.getAttribute("user");
        model.addAttribute("user", userVo);
        if(userVo == null){
            model.addAttribute("alert","로그인이 필요한 페이지입니다.");
            model.addAttribute("url","/login");
        }
        else{
            return "bbs/boardPost";
        }
        System.out.println("밸류값" + userVo);
        return "alert";
    }

    // Q&A 글작성
    @PostMapping("/boardPost")
    public String boardPost1(@ModelAttribute BbsVo bbsVo) {
        int insertResult = bd.insertBoard(bbsVo);
        bd.updateBoardNum();
        if (insertResult > 0) {           // 성공으로 판단되면 목록으로 돌아가기.
            return "redirect:board/";
        } else {                          // 실패하면 글작성 페이지에 머무름.
            return "bbs/boardPost";
        }
    }

    // Q&A 글 상세조회
    @GetMapping("/boardDetail")
    public String boardDetail(Model model, CommentVo commentVo, BbsVo bbsVo, HttpSession session, int board_num) {
        BbsVo bbs = bd.selectBoard(board_num);
        model.addAttribute("bbs", bbs);
        log.info(bbs);
        session.getAttribute("user");
        model.addAttribute("board_num", board_num);

        // 댓글 조회
        List<CommentVo> com = cd.selectAllComment(board_num);
        log.info("com>>>>>>"+com);
        model.addAttribute("com", com);

        return "bbs/boardDetail";
    }


    // Q&A 글 수정/삭제 폼
    @GetMapping("/updateBoard1")
    public String updateBoard1(BbsVo bbsVo, Model model, int board_num, HttpSession session1) {
        BbsVo bbs = bd.selectBoard(bbsVo.getBoard_num());
        model.addAttribute("bbs", bbs);
        return "bbs/boardUpdate";
    }

    // Q&A 글 수정
    @PostMapping("/updateBoard2")
    public String updateBoard2(BbsVo bbsVo) {
        log.info("보드값" + bbsVo.getBoard_num() );
        BbsVo bbs = bd.selectBoard(bbsVo.getBoard_num());
        log.info(bbs);
        int str = bd.updateBoard(bbsVo);
        log.info("업데이트 :" + str);
        return "redirect:board";
    }

    // Q&A 글 삭제
    @GetMapping("/deleteBoard")
    public String deleteBoard(int board_num) {
        int str = bd.deleteBoard(board_num);
        int str1 = bd.updateBoardNum();
        return "redirect:board";
    }


}