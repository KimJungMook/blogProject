package com.jungmook.blog.Service;

import com.jungmook.blog.Repository.UserRepository;
import com.jungmook.blog.dto.ResponseDto;
import com.jungmook.blog.dto.SignUpDto;
import com.jungmook.blog.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    public ResponseDto<?> signUp(SignUpDto signUpDto){
        String userEmail = signUpDto.getUserEmail();
        String userPassword = signUpDto.getUserPassword();
        String userPasswordCheck = signUpDto.getUserPasswordCheck();
        try {
            // email 중복 확인
            if(userRepository.existsById(userEmail)){
                return ResponseDto.setFailed("existed Email!!");
            }
        }catch (Exception e){
            return ResponseDto.setFailed(("Data Base Error!"));
        }
        // 비밀번호 다른지 체크
        if(!userPassword.equals(userPasswordCheck)){
            return ResponseDto.setFailed("password does not matched");
        }
        // UserEntity 생성
        UserEntity userEntity = new UserEntity(signUpDto);
        try{
            //userRepository를 이용해서 데이터베이스에 Entity 저장!
            userRepository.save(userEntity);
        }catch (Exception e){
            ResponseDto.setFailed("DataBase 에러");
        }
        // 성공시 success 반환
        return ResponseDto.setSuccess("SignUp Success", null);
    }
}
