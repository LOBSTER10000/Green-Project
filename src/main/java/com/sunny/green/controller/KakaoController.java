package com.sunny.green.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunny.green.dao.UserDao;
import com.sunny.green.vo.KakaoProfileVo;
import com.sunny.green.vo.OAuthTokenVo;
import com.sunny.green.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Log4j2
public class KakaoController {


    private final UserDao ud;
    @GetMapping("/auth/kakao/callback")
    public String Callback(String code, Model model, HttpSession session) {
        //데이터를 리턴해주는 컨트롤러
        //post방식으로 key=value 방식으로 데이터를 요청

        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","7033be3a22ffb05c2b13405c4f9a65ac");
        params.add("redirect_uri","http://15.165.155.88:8080/auth/kakao/callback");
        params.add("code", code);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest
                = new HttpEntity<>(params,headers);

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );


        /* response 데이터를 오브젝트에 담기위하여 gson,json, objectMapper를 사용할 수 있음 */
        ObjectMapper obMapper = new ObjectMapper();
        OAuthTokenVo oauthToken = null;
        try {
            oauthToken = obMapper.readValue(response.getBody(), OAuthTokenVo.class);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.info("카카오 액세스 토큰 : " + oauthToken.getAccess_token());

        /* 토큰을 이용한 사용자 정보 보기 */
        RestTemplate rt2 = new RestTemplate();

        /*HttpHeader 오브젝트 생성*/
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());

        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        /* exchange라는 함수가 HttpEntity로 오브젝트를 받기 때문에 하나의 오브젝트에 담음 */
        /* Http 요청하기 - 요청주소, get방식, response변수의 응답 */
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        log.info(response2.getBody());


        /* response 데이터를 오브젝트에 담기위하여 gson,json, objectMapper를 사용할 수 있음 */
        ObjectMapper obMapper2 = new ObjectMapper();
        KakaoProfileVo kakaoProfileVo = null;
        try {
            kakaoProfileVo = obMapper2.readValue(response2.getBody(), KakaoProfileVo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("카카오 아이디 : " + kakaoProfileVo.getId());
        log.info("카카오 이메일 : " + kakaoProfileVo.getKakao_account().getEmail());
        log.info("카카오 이름 : " + kakaoProfileVo.getProperties().getNickname());
        UUID testPass = UUID.randomUUID();
        log.info("카카오 서버 패스워드 : " + testPass);

        UserVo user = new UserVo();
        user.setUser_id(kakaoProfileVo.getId());
        user.setUser_email(kakaoProfileVo.getKakao_account().getEmail());
        user.setUser_name(kakaoProfileVo.getProperties().getNickname());
        user.setUser_pass(testPass.toString());
        user.setUser_tel("0100000000");
        UserVo userDB = ud.selectAll1(user.getUser_id());
        if (userDB != null) {
            System.out.println("카카오 정보 찾았습니다 : " + user);
            if (userDB.getUser_name() != null) {
                model.addAttribute("alert", userDB.getUser_name() + "님 반갑습니다");
            }
            model.addAttribute("url", "/index");
            session.setAttribute("user", userDB);
        } else {
            log.info("카카오 정보 저장해야죠? :" + user);
            ud.joinUser(user);
            model.addAttribute("alert", "회원가입을 했습니다");
            model.addAttribute("url", "/index");
            session.setAttribute("user", user);
        }
        return "alert";
    }
}
