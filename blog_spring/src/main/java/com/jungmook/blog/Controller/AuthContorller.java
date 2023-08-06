package com.jungmook.blog.Controller;

import com.jungmook.blog.Service.AuthService;
import com.jungmook.blog.dto.ResponseDto;
import com.jungmook.blog.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthContorller {

    @Autowired
    private AuthService authService;
    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody){
        ResponseDto<?> result = authService.signUp(requestBody);
        return result;
    }
}
