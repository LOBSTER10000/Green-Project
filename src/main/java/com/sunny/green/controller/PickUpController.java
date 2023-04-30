package com.sunny.green.controller;


import com.sunny.green.dao.UserDao;
import com.sunny.green.service.PickupServiceImpl;
import com.sunny.green.vo.PickupAddressVo;

import com.sunny.green.vo.PickupInfoVo;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PickUpController {

    private final PickupServiceImpl pSI;
    private final UserDao ud;

    // 예약 첫번째 페이징
    @GetMapping("/pickup")
    public String pickupPage(HttpSession session, Model model) {
        if(session.getAttribute("user") != null) {
            return "pickup/pickUp";
        } else {
            model.addAttribute("alert", "로그인이 필요한 페이지입니다.");
            model.addAttribute("url", "/login");
            return "alert";
        }
    }

    // 예약 첫번째 페이지 입력값 전달
    @PostMapping("pickupSave.do")
    @ResponseBody
    public void pickupPageSave(PickupAddressVo address, HttpSession session) {
        session.setAttribute("address", address);
    }
    @PostMapping("pickupSave2.do")
    @ResponseBody
    public void pickupPageSave(PickupInfoVo info, HttpSession session) {
        session.setAttribute("info", info);
    }
    //이미지 임시 저장
    @PostMapping("pickupImg.do")
    @ResponseBody
    public void pickupImg(@RequestParam("images") List<MultipartFile> files, HttpSession session) {
        session.setAttribute("pickupImg", files);
    }

    // 예약 두번째 페이징
    @GetMapping("/pickup2")
    public String pickupPage2() {

        return "pickup/pickUp2";
    }

    // 예약 세번째 페이징
    @GetMapping("/pickup3")
    public String pickupPage3() {
        return "pickup/pickUp3";
    }

//    @GetMapping("pickupRealSave.do")
//    @ResponseBody
//    public void pickupRealSave(HttpSession session) {
//        PickupAddressVo address = (PickupAddressVo) session.getAttribute("address");
//        PickupInfoVo info = (PickupInfoVo) session.getAttribute("info");
//        int successVal = pSI.pickupAddress(address);
//        if(successVal==1) {
//            int addressNo = address.getPu_address_no();
//            info.setPu_address_no(addressNo);
//            System.out.println("info>>>>>>>>>>>"+info);
//            int successVal2 = pSI.pickupInfo(info);
//            if(successVal2==1) {
//                int infoNo = info.getPu_no();
//                String imgVal = info.getPu_img();
//                if(Objects.equals(imgVal, "Y")) {
//                    List<MultipartFile> pickupImg = (List<MultipartFile>) session.getAttribute("pickupImg");
//                    int successVal3 = pSI.pickupImg(pickupImg, infoNo);
//                }
//            }
//        }
//    }

    @GetMapping("/reservationBd")
    public String reservationBd(HttpSession session, Model model) {

        if(session.getAttribute("user") == null){
            model.addAttribute("alert", "로그인이 필요한 페이지입니다.");
            model.addAttribute("url", "/login");
        }
        else{
            UserVo user = (UserVo) session.getAttribute("user");
            UserVo user1 = ud.selectAll1(user.getUser_id());
            System.out.println("번호는 뭘까요? : " + user1);
            model.addAttribute("user", user1);

            return "myPage/reservationBd";
        }
        return "alert";
    }

}
