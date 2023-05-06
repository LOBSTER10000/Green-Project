package com.sunny.green.controller;


import com.sunny.green.dao.UserDao;
import com.sunny.green.service.PickupServiceImpl;
import com.sunny.green.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
@Log4j2
public class PickUpController {


    private final PickupServiceImpl pSI;


    private final UserDao ud;

    // 예약 첫번째 페이징
    @GetMapping("/pickup")
    public String pickupPage(HttpSession session, Model model) {
        if(session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
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
        List<Integer> imgNo = pSI.pickupImg(files);
        if (imgNo != null) {
            session.setAttribute("imgNo", imgNo);
        }
    }

    // 예약 두번째 페이징
    @GetMapping("/pickup2")
    public String pickupPage2() {

        return "pickup/pickUp2";
    }

    // 예약 세번째 페이징
    @GetMapping("/pickup3")
    public String pickupPage3(@RequestParam(name = "categoryVal") List<String> categoryVals,
                              @RequestParam(name = "countVal") List<String> countVals,
                              Model model, HttpSession session) {
        List<Map<String, String>> items = new ArrayList<>();
        for (int i = 0; i < categoryVals.size(); i++) {
            Map<String, String> item = new HashMap<>();
            item.put("categoryVal", categoryVals.get(i));
            item.put("countVal", countVals.get(i));
            items.add(item);
        }
        System.out.println("items>>>>>>"+items);
        List<PickupCategoryVo> changeItems = pSI.pickupCategorySet(items);
        System.out.println("changeItems>>>>>>"+changeItems);

        PickupInfoVo info = (PickupInfoVo) session.getAttribute("info");
        String imgVal = info.getPu_img();
        if (Objects.equals(imgVal, "Y")) {
            List<Integer> imgNo = (List<Integer>) session.getAttribute("imgNo");
            List<PickupImgVo> pickupImg = pSI.pickupImgView(imgNo);
            System.out.println("pickupImg>>>>>>"+pickupImg);
            model.addAttribute("pickupImg", pickupImg);
        } else {
            model.addAttribute("pickupImg", "None");
        }
        session.setAttribute("changeItems", changeItems);
        model.addAttribute("items", items);
        return "pickup/pickUp3";
    }

    @GetMapping("/pickupRealSave")
    public String pickupRealSave(HttpSession session, Model model) {
        PickupAddressVo address = (PickupAddressVo) session.getAttribute("address");
        PickupInfoVo info = (PickupInfoVo) session.getAttribute("info");
        List<PickupCategoryVo> items = (List<PickupCategoryVo>) session.getAttribute("changeItems");
        System.out.println("마지막items>>>>>>"+items);

        int successVal = pSI.pickupAddress(address);
        if(successVal==1) {
            int addressNo = address.getPu_address_no();
            info.setPu_address_no(addressNo);
            int successVal2 = pSI.pickupInfo(info);
            if(successVal2==1) {
                int infoNo = info.getPu_no();
                int categoryResult = pSI.pickupCategoryInsert(items, infoNo);
                String imgVal = info.getPu_img();
                if(Objects.equals(imgVal, "Y")) {
                    List<Integer> imgNo = (List<Integer>) session.getAttribute("imgNo");
                    int successVal3 = pSI.pickupImgInfoNo(imgNo, infoNo);
                }
            }
        }
        model.addAttribute("alert", "수거 예약되었습니다.");
        model.addAttribute("url", "/index");
        return "alert";
    }

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

    @GetMapping("/img/pickupUpload/{pu_img_save_name}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable("pu_img_save_name") String imgSaveName) throws IOException {
        Resource resource = new FileSystemResource("src/main/resources/static/img/pickupUpload/" + imgSaveName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }

}
