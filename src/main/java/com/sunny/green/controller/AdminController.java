package com.sunny.green.controller;


import com.sunny.green.dao.AdminDao;
import com.sunny.green.dao.PickupDao;
import com.sunny.green.dao.UserDao;
import com.sunny.green.service.UserService;
import com.sunny.green.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class AdminController {



    private final UserDao ud;
    private final AdminDao ad;


    private UserService userService;





    @PostMapping("/admin")
    public String admin2(AdminVo av, Model mo, HttpSession session){
        AdminVo adminVo = ad.selectAdmin(av);
        if(adminVo != null) {
            if (adminVo.getAdmin_role() == 1) {
                mo.addAttribute("alert", "관리자용 로그인에 성공했습니다");
                mo.addAttribute("url", "/admin/main");
                session.setAttribute("admin", adminVo);
                System.out.println(session.getAttribute("admin"));
            } else {
                mo.addAttribute("alert", "아이디와 비밀번호를 다시 확인하십시오");
                mo.addAttribute("url", "/admin");
            }
        return "alert";
        } else{
            mo.addAttribute("alert", "관리자용 아이디가 존재하지 않습니다");
            mo.addAttribute("url", "/index");
        }
        return "alert";
    }

    @GetMapping("/admin/main")
    public String admin1() {

        return "admin/admin_main";
    }



    @GetMapping("/admin/reservation")
    public String adminRe() {
        return "/admin/admin_reservation";
    }

    @GetMapping("/admin/user1")
    public String adminUs1(){
        return "/admin/admin_user1";
    }

    //보영 (회원 목록 조회)
    @GetMapping("/admin/user2")
    public String getUserList(Model model) {
        List<UserVo> user = ud.selectAll();
        model.addAttribute("user", user);
        return "/admin/admin_user2";
    }

    @GetMapping("/admin/bbs1")
    public String bbs1(){
        return "/admin/admin_bbs1";
    }

    @GetMapping("/admin/bbs2")
    public String bbs2(){
        return "/admin/admin_bbs2";
    }

    @GetMapping("admin/product1")
    public String pro1(Model mo) {
        List<ProductVo> product = ad.selectProAll();
        mo.addAttribute("product", product);
        return "/admin/admin_product1";
    }

    @GetMapping("admin/product2")
    public String pro2(Model mo, ProductVo vo, ProImgVo iv) {
        ProductVo product = ad.selectPro(vo.getPro_num());
        ProImgVo proImgVo = ad.selectImg(iv.getPro_num());
        mo.addAttribute("product", product);
        mo.addAttribute("proImgVo", proImgVo);
        return "/admin/admin_product2";
    }

    @GetMapping("admin/product3")
    public String pro3() {


        return "/admin/admin_product3";
    }

    @GetMapping("admin/deletePro")
    public String deletePro(int pro_num) {
        int otr = ad.deletePro_img(pro_num);
        int str = ad.deletePro(pro_num);
        return "redirect:/admin";
    }

    @GetMapping("admin/product4")
    public String pro5(ProductVo productVo, Model mo) {
        ProductVo pro = ad.selectPro(productVo.getPro_num());
        mo.addAttribute("pro", pro);
        return "/admin/admin_product4";
    }

    @PostMapping("/updatePro")
    public String pro6(ProductVo productVo) {
        int str = ad.updatePro(productVo);
        return "redirect:/admin/product1";
    }


    @Transactional
    @PostMapping("/product3")
    public String pro4(ProductVo productVo, @RequestParam("image") MultipartFile imageFile) {
        String fileName = imageFile.getOriginalFilename(); // 파일 이름 추출
        String uploadPath = "src/main/resources/static/img/product/"; // 업로드 디렉토리 경로
        String filePath = uploadPath + fileName; // 저장될 파일 경로
        String uuid = UUID.randomUUID().toString();
        String realPath = uploadPath + uuid + fileName;
        String saveFile = uuid + fileName;
        System.out.println(fileName);
        System.out.println(filePath);
        System.out.println(realPath);
        ad.insertPro(productVo);
        String str = String.valueOf(productVo);
        System.out.println(str);

        try (OutputStream os = new FileOutputStream(realPath)) {
            os.write(imageFile.getBytes());

            ProImgVo proImgVo = ProImgVo.builder()
                    .pro_num(productVo.getPro_num())
                    .pro_img_save_name(saveFile)
                    .pro_img_path(realPath)
                    .build();
            ad.insertProImg(proImgVo);
        } catch (IOException e) {
            // 파일 저장 실패 시 예외 처리
            e.printStackTrace();
        }
        return "redirect:/admin";
    }


}


