package com.ironman.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironman.memo.user.domain.User;
import com.ironman.memo.user.service.UserService;

@RequestMapping("/user")
@RestController // @Controller + @ResponseBody
public class UserRestController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/join")
    public Map<String, String> join(
            @RequestParam("loginId") String loginId
            , @RequestParam("password") String password
            , @RequestParam("name") String name
            , @RequestParam("email") String email) {
        
        User user = userService.addUser(loginId, password, name, email);
        Map<String, String> resultMap = new HashMap<>();
        
        if(user != null) {
            // 성공
            resultMap.put("result", "success");
        } else {
            // 실패
            resultMap.put("result", "fail");
        }
        return resultMap;
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestParam("loginId") String loginId
            , @RequestParam("password") String encryptPassword
            , HttpServletRequest request) {
        
        User user = userService.getUser(loginId, encryptPassword);
        Map<String, String> resultMap = new HashMap<>();
        if(user != null) {
            resultMap.put("result", "success");
            // 세션에 로그인이 된 상태를 저장하면 됨
            // 세션 객체
            HttpSession session = request.getSession();
            // 세션에 로그인된 사용자 정보를 저장
            // 세션에 userId 라는 키로 값이 저장되어 있으면 로그인 된 상태
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());

        } else {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }
    
    
}