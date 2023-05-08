package com.sunny.green.controller;

import com.sunny.green.dao.AdminDao;
import com.sunny.green.dao.PickupDao;
import com.sunny.green.dao.UserDao;
import com.sunny.green.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final UserDao ud;
    private final AdminDao ad;
    private final PickupDao pd;


    @GetMapping("/admin")
    public String admin() {
        return "admin/admin_login";
    }

    @PostMapping("/admin")
    public String admin2(AdminVo av, Model mo, HttpSession session) {
        AdminVo adminVo = ad.selectAdmin(av);
        if (adminVo != null) {
            if (adminVo.getAdmin_role() == 1) {
                mo.addAttribute("alert", "관리자용 로그인에 성공했습니다.");
                mo.addAttribute("url", "/admin/main");
                session.setAttribute("admin", adminVo);
                log.info(session.getAttribute("user"));
            } else {
                mo.addAttribute("alert", "아이디/비밀번호가 일치하지 않습니다.");
                mo.addAttribute("url", "/admin");
            }
            return "alert";
        } else {
            mo.addAttribute("alert", "관리자용 아이디가 존재하지 않습니다.");
            mo.addAttribute("url", "/index");
        }
        return "alert";
    }

    @GetMapping("/admin/main")
    public String admin1() {

        return "admin/admin_main";
    }




    @GetMapping("/admin/user1")
    public String adminUs1() {
        return "admin/admin_user1";
    }

    // 검색
    @GetMapping("/admin/user2")
    public String getUserList(Model model, PageVo search, @RequestParam(required = false) String searchType, @RequestParam(required = false) String searchValue) throws Exception {
        List<UserVo> user;
        if (searchType == null || searchValue == null) {
            user = ud.selectAll();
        } else {
            user = ud.selectAll2(search, searchType, searchValue);
        }
        model.addAttribute("user", user);
        return "admin/admin_user2";
    }

    @PostMapping("/pagination")
    @ResponseBody
    public List<UserVo> getUserData(PageVo search, @RequestParam(required = false) String searchType, @RequestParam(required = false) String searchValue) {
        List<UserVo> user;
        if (searchType == null || searchValue == null) {
            user = ud.selectAll();
        } else {
            user = ud.selectAll2(search, searchType, searchValue);
        }
        System.out.println(">>>>>>>>>>>>>"+user);
        return user;
    }

    // 보영 (회원정보상세)
    @GetMapping("/admin/modify")
    public String userDetail(Model model, UserVo userVo) {
        UserVo user = ud.selectAll1(userVo.getUser_id());
        model.addAttribute("user", user);
        log.info(user);
        return "admin/admin_user3";
    }

    // 보영 (회원정보수정)
    @PostMapping("/admin/modify")
    public String adminModify(UserVo user, Model model) {
        int update = ud.updateUser(user);
        System.out.println(update);
        model.addAttribute("alert", "수정되었습니다");
        model.addAttribute("url", "/admin/user2");

        return "alert";
    }


    @GetMapping("admin/delete")
    public String deleteUser(@RequestParam("user_id") String user_id) {
        log.info("번호 :" + user_id);
        int deleteUser = ud.deleteId(user_id);
        log.info(deleteUser);
        return "redirect:user2";
    }


    @GetMapping("/admin/bbs1")
    public String bbs1() {
        return "admin/admin_bbs1";
    }

    @GetMapping("/admin/bbs2")
    public String bbs2() {
        return "admin/admin_bbs2";
    }

    @GetMapping("admin/product1")
    public String pro1(Model mo) {
        List<ProductVo> product = ad.selectProAll();
        mo.addAttribute("product", product);
        return "admin/admin_product1";
    }

    @GetMapping("admin/product2")
    public String pro2(Model mo, ProductVo vo, ProImgVo iv) {
        ProductVo product = ad.selectPro(vo.getPro_num());
        ProImgVo proImgVo = ad.selectImg(iv.getPro_num());
        mo.addAttribute("product", product);
        mo.addAttribute("proImgVo", proImgVo);
        return "admin/admin_product2";
    }

    @GetMapping("admin/product3")
    public String pro3() {


        return "admin/admin_product3";
    }

    @GetMapping("admin/deletePro")
    public String deletePro(int pro_num) {
        int otr = ad.deletePro_img(pro_num);
        int str = ad.deletePro(pro_num);
        return "redirect:admin";
    }

    @GetMapping("admin/product4")
    public String pro5(ProductVo productVo, Model mo) {
        ProductVo pro = ad.selectPro(productVo.getPro_num());
        mo.addAttribute("pro", pro);
        return "admin/admin_product4";
    }

    @PostMapping("/updatePro")
    public String pro6(ProductVo productVo) {
        int str = ad.updatePro(productVo);
        return "redirect:admin/product1";
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
        ad.insertPro(productVo);
        String str = String.valueOf(productVo);
        log.info(str);

        try (OutputStream os = new FileOutputStream(realPath)) {
            os.write(imageFile.getBytes());

            ProImgVo proImgVo = new ProImgVo();
            proImgVo.setPro_num(productVo.getPro_num());
            proImgVo.setPro_img_save_name(saveFile);
            proImgVo.setPro_img_path(realPath);
            ad.insertProImg(proImgVo);
        } catch (IOException e) {
            // 파일 저장 실패 시 예외 처리
            e.printStackTrace();
        }
        return "redirect:admin";
    }
//   ---------------------------------- rs


    //rs_검색 및 데이터 불러오기
    @GetMapping("/admin/reservation")
    public String getPickupList(Model model, PageVo search, @RequestParam(required = false) String searchType_rs, @RequestParam(required = false) String searchValue_rs) throws Exception {
        List<PickupDetailVo> pickup;
        if (searchType_rs == null || searchValue_rs == null) {
            pickup = pd.rsList();
            log.info(pickup);
        } else {
            pickup = pd.rsList2(search, searchType_rs, searchValue_rs);
            log.info(pickup);
        }
        model.addAttribute("pickup", pickup);
        return "admin/admin_reservation";
    }
    // rs_페잉징
    @PostMapping("/pagination/rs_page")
    @ResponseBody
    public List<PickupDetailVo> getPickupInfo(PageVo search, @RequestParam(required = false) String searchType, @RequestParam(required = false) String searchValue) {
        List<PickupDetailVo> pickup;
        if (searchType == null || searchValue == null) {
            pickup = pd.rsList();
            log.info("제발 되주십시오" + pickup);
        } else {
            pickup = pd.rsList2(search, searchType, searchValue);
            log.info("되나?1" + pickup);
        }
        System.out.println(">>>>>>>>>>>>>"+pickup);
        return pickup;
    }

    // 예약정보상세
    @GetMapping("/rs_info")
    public String rsDetail(Model model, PickupDetailVo  pickupDetailVo ) {
        log.info(pickupDetailVo);
        PickupDetailVo rs_info = pd.rs_info(pickupDetailVo.getPu_no(),pickupDetailVo.getPu_address_no());
        model.addAttribute("rs_info", rs_info);
        log.info(rs_info);
        return "admin/admin_rs_info";
    }



    @GetMapping("/img/product/{img_save_name}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable("img_save_name") String imgSaveName) throws IOException {
        Resource resource = new FileSystemResource("src/main/resources/static/img/product/" + imgSaveName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }

}


