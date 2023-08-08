package com.jungmook.blog.Controller;

import com.jungmook.blog.Service.AuthService;
import com.jungmook.blog.dto.ResponseDto;
import com.jungmook.blog.dto.SignInResponseDto;
import com.jungmook.blog.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jungmook.blog.dto.SignInDto;
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

    @PostMapping("/signIn")
    public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody){
        ResponseDto<SignInResponseDto> result = authService.signIn(requestBody);
        return result;
    }
}
