package com.sunny.green.controller;

import com.sunny.green.dao.*;
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
public class UserController {

    private final UserDao ud;


    private final AdminDao ad;


    private final ExchangeDao ed;

//    private final MailService ms;


    private final MailDao md;


    private final ProfileImgDao pid;


    //마이페이지 매핑
    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model mo) {
        if (session.getAttribute("user") == null) {
            mo.addAttribute("alert", "로그인이 필요한 페이지입니다.");
            mo.addAttribute("url", "/login");
        } else {
            UserVo userDB = (UserVo) session.getAttribute("user");
            UserVo user = ud.selectAll1(userDB.getUser_id());
            ProfileImgVo profileImgVo = pid.selectProfileImg(userDB.getUser_id());
            mo.addAttribute("user", user);
            mo.addAttribute("profileImgVo", profileImgVo);
            return "myPage/myPage";
        }

        return "alert";
    }

    //로그인 창에 들어갈때 쓰는 매핑
    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("alert", "이미 로그인이 된 상태입니다.");
            model.addAttribute("url", "/index");
        } else {
            return "user/login";
        }
        return "alert";
    }

    //로그인 할 때 나타나는 post매핑
    @PostMapping("/login")
    public String login1(UserVo user, HttpSession session, Model model) {
        UserVo userDB = ud.selectUser(user);
        log.info(userDB);

        if (userDB != null) {
            System.out.println(userDB);
            session.setAttribute("user", userDB);
            model.addAttribute("alert", "로그인에 성공했습니다.");
            model.addAttribute("url", "/index");
        } else {
            System.out.println("실패했습니다");
            model.addAttribute("alert", "아이디/비밀번호가 일치하지 않습니다");
            model.addAttribute("url", "/login");
        }

        return "alert";
    }


    //회원가입 들어가는 매핑
    @GetMapping("/join")
    public String join(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("alert", "이미 로그인이 된 상태입니다.");
            model.addAttribute("url", "/index");
        } else {
            return "user/join";
        }
        return "alert";
    }

    //회원가입 기능
    @PostMapping("/join")
    public String join1(UserVo user, Model model, HttpSession session) {

        if (ud.joinUser(user) != 0) {
            AdminVo adminVo = new AdminVo();
            adminVo.setAdmin_id(user.getUser_id());
            adminVo.setAdmin_pass(user.getUser_pass());
            adminVo.setUser_id(user.getUser_id());
            adminVo.setUser_pass(user.getUser_pass());
            ad.insertAdmin(adminVo);
            model.addAttribute("alert", "회원가입이 완료되었습니다.");
            model.addAttribute("url", "/index");
            session.setAttribute("user", user);
        }
        return "alert";
    }

    @GetMapping("/breakDown")
    public String exchange(HttpSession session, Model mo) {
        if (session.getAttribute("user") == null) {
            mo.addAttribute("alert", "로그인이 필요한 페이지입니다.");
            mo.addAttribute("url", "/login");
        } else {
            UserVo userDB = (UserVo) session.getAttribute("user");
            mo.addAttribute("user", userDB);
            List<ExchangeVo> ex = ed.selectExchangeId(userDB.getUser_id());
            mo.addAttribute("ex", ex);
            ProfileImgVo profileImgVo = pid.selectProfileImg(userDB.getUser_id());
            mo.addAttribute("profileImgVo", profileImgVo);
            return "myPage/breakDown";
        }

        return "alert";
    }

    //로그아웃 기능
    @GetMapping("/logout")
    public String logout(HttpSession httpSession, Model mo) {
        if (httpSession.getAttribute("user") == null) {
            mo.addAttribute("alert", "로그인이 필요한 페이지입니다.");
            mo.addAttribute("url", "/login");
        } else {
            httpSession.setAttribute("user", null);
            mo.addAttribute("alert", "로그아웃되었습니다.");
            mo.addAttribute("url", "/index");
        }
        return "alert";
    }

    //회원가입시 아이디 중복 체크
    @PostMapping("/checkDuplicateId")
    @ResponseBody
    public String checkDuplicateId(@RequestParam("user_id") String userid) {
        UserVo existingUser = ud.selectUserId(userid);
        if (existingUser != null) {
            return "exist";
        } else {
            return "not exist";
        }
    }


    // 마이페이지 개인정보 수정
    @GetMapping("/modify")
    public String modify(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("alert", "로그인이 필요한 페이지입니다.");
            model.addAttribute("url", "/login");
        } else {
            UserVo user = (UserVo) session.getAttribute("user");
            UserVo user1 = ud.selectAll1(user.getUser_id());
            log.info("번호는 뭘까요? : " + user1);
            ProfileImgVo profileImgVo = pid.selectProfileImg(user.getUser_id());
            model.addAttribute("profileImgVo", profileImgVo);
            model.addAttribute("user", user1);
            model.addAttribute("aaa", "bbb");
            return "myPage/modify";
        }
        return "alert";
    }

    @PostMapping("/modify")
    public String modify1(UserVo user, Model mo) {
        int update = ud.updateUser(user);
        if (update == 1) {
            System.out.println(update);
            mo.addAttribute("alert", "정보가 수정되었습니다");
            mo.addAttribute("url", "/myPage");

        } else {
            mo.addAttribute("alert", "정보를 수정하는데 오류가 있습니다");
            mo.addAttribute("url", "/index");
        }
        return "alert";
    }

    //마이페이지 그린포인트 확인
    @GetMapping("/greenPoint")
    public String green(HttpSession session, Model mo) {
        if (session.getAttribute("user") == null) {
            mo.addAttribute("alert", "로그인이 필요한 페이지입니다.");
            mo.addAttribute("url", "/login");
        } else {
            UserVo userDB = (UserVo) session.getAttribute("user");
            UserVo user1 = ud.selectAll1(userDB.getUser_id());
            mo.addAttribute("user", user1);
            ProfileImgVo profileImgVo = pid.selectProfileImg(userDB.getUser_id());
            mo.addAttribute("profileImgVo", profileImgVo);
            return "/myPage/greenPoint";
        }

        return "alert";
    }

    @GetMapping("/delete")
    public String delete(HttpSession session, String user_id) {
        UserVo userDB = (UserVo) session.getAttribute("user");
        user_id = userDB.getUser_id();
        log.info(user_id);
        int delete = ud.deleteId(user_id);
        log.info(delete);
        session.setAttribute("user", null);
        return "redirect:index";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }


    @Transactional
    @PostMapping("/uploadProfile")
    public String pro4(ProductVo productVo, @RequestParam("file") MultipartFile imageFile, HttpSession session) {
        String fileName = imageFile.getOriginalFilename(); // 파일 이름 추출
        String uploadPath = "/home/ubuntu/greentopia2/img/profile/"; // 업로드 디렉토리 경로
        String filePath = uploadPath + fileName; // 저장될 파일 경로
        String uuid = UUID.randomUUID().toString();
        String realPath = uploadPath + uuid + fileName;
        String saveFile = uuid + fileName;

        try (OutputStream os = new FileOutputStream(realPath)) {
            os.write(imageFile.getBytes());

            UserVo userVo = (UserVo) session.getAttribute("user");
            ProfileImgVo profileImgVo = new ProfileImgVo();
            profileImgVo.setUser_id(userVo.getUser_id());
            profileImgVo.setImg_save_name(saveFile);
            profileImgVo.setImg_path(realPath);
            pid.insProfileImg(profileImgVo);
        } catch (IOException e) {
            // 파일 저장 실패 시 예외 처리
            e.printStackTrace();
        }
        return "redirect:myPage";
    }

    @GetMapping("/img/profile/{img_save_name}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable("img_save_name") String imgSaveName) throws IOException {
        Resource resource = new FileSystemResource("/home/ubuntu/greentopia2/img/profile/" + imgSaveName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }

}
