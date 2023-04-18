package com.sunny.green.controller;

import com.sunny.green.dao.AdminDao;
import com.sunny.green.dao.ExchangeDao;
import com.sunny.green.dao.UserDao;
import com.sunny.green.vo.ExchangeVo;
import com.sunny.green.vo.ProductWithImgVo;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class ExchangeController {
    private final AdminDao ad;
    private final UserDao ud;
    private final ExchangeDao ed;

    @GetMapping("/exchange")
    public String exchange1(HttpSession session, Model mo){
        if(session.getAttribute("user") == null){
            mo.addAttribute("alert", "로그인을 먼저 해주시기 바랍니다");
            mo.addAttribute("url", "/login");
        }
        else{
            List<ProductWithImgVo> pv = ed.selectProductAll();
            mo.addAttribute("pv", pv);
            return "/exchange/exchange1";
        }
        return "/alert";
    }

    @GetMapping("/exchange2")
    public String exchange(HttpSession httpSession, Model mo, ProductWithImgVo productWithImgVo){
        ProductWithImgVo pro = ed.selectProOne(productWithImgVo.getPro_num());
        mo.addAttribute("pro", pro);
        System.out.println(pro);
        UserVo uservo = (UserVo) httpSession.getAttribute("user");
        UserVo user = ud.selectAll1(uservo.getUser_id());
        mo.addAttribute("user", user);
        System.out.println("왜 변화가 안되는 것인가" + user);
        return "/exchange/exchange2";
    }

    @PostMapping("/exchange")
    public String exchange(ExchangeVo ev, UserVo user, RedirectAttributes redirectAttributes){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // 예약번호로 사용할 문자열

        int length = 8; // 예약번호의 길이
        Random random = new Random();
        StringBuilder reservationId = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            reservationId.append(chars.charAt(index));
        }

        System.out.println("코드 번호 :" + reservationId);
        ev.setEx_uuid_num(String.valueOf(reservationId));

        int result = ed.insertExchange(ev);
        System.out.println("저장됨?" + result);


        int remain_point = ev.getRemain_point();
        user.setUser_point(remain_point);
        ud.updatePoint(user);
        System.out.println("유저 포인트값 :" + remain_point );


        redirectAttributes.addAttribute("ex_num", ev.getEx_num());
        return "redirect:/exchange3";
    }

    @GetMapping("/exchange3")
    public String exchange3(Model mo, @RequestParam("ex_num") int ex_num){
        ExchangeVo ev = ed.selectExchangeOne(ex_num);
        mo.addAttribute("ev", ev);
        return "/exchange/exchange3";
    }


}
