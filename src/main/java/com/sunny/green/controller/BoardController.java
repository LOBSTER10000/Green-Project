package com.sunny.green.controller;

import com.sunny.green.dao.BbsDao;
import com.sunny.green.vo.BbsVo;
import com.sunny.green.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import com.sunny.green.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BbsDao bd;
    private final UserDao ud;

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
    public String boardPost1(@ModelAttribute BbsVo bbsVo, HttpSession session) {
        int insertResult = bd.insertBoard(bbsVo);
        bd.updateBoardNum();
        if (insertResult > 0) {           // 성공으로 판단되면 목록으로 돌아가기.
            return "redirect:/board/";
        } else {                          // 실패하면 글작성 페이지에 머무름.
            return "bbs/boardPost";
        }
    }

    // Q&A 글 상세조회
    @GetMapping("/boardDetail")
    public String boardDetail(Model model, BbsVo bbsVo, HttpSession session) {
        BbsVo bbs = bd.selectBoard(bbsVo.getBoard_num());
        model.addAttribute("bbs", bbs);
        System.out.println(bbs);
        session.getAttribute("user");
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
    public String updateBoard2(BbsVo bbsVo, Model model) {
        System.out.println("보드값" + bbsVo.getBoard_num() );
        BbsVo bbs = bd.selectBoard(bbsVo.getBoard_num());
        System.out.println(bbs);
        int str = bd.updateBoard(bbsVo);
        System.out.println("업데이트 :" + str);
        return "redirect:/board";
    }

    // Q&A 글 삭제
    @GetMapping("/deleteBoard")
    public String deleteBoard(int board_num) {
        int str = bd.deleteBoard(board_num);
        int str1 = bd.updateBoardNum();
        return "redirect:/board";
    }

}