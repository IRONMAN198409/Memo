package com.ironman.memo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironman.memo.common.EncryptUtils;
import com.ironman.memo.user.domain.User;
import com.ironman.memo.user.repositoy.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User addUser(
            String loginId
            , String password
            , String name
            , String email) {


        // password 암호화
        String encryptPassword = EncryptUtils.md5(password);

        
        User user = User.builder()
                .loginId(loginId)
                .password(encryptPassword)  // 암호화된 패스워드를 적용한다
                .name(name)
                .email(email)
                .build();
        
        
        user = userRepository.save(user);
        return user;
    }

    public User getUser(String loginId, String password) {
        
        String encryptPassword = EncryptUtils.md5(password);
        List<User> userList = userRepository.findByLoginIdAndPassword(loginId, encryptPassword);

        // 비워진 경우
        if(userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }
    
    

    
}