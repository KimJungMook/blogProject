package com.jungmook.blog.Service;

import com.jungmook.blog.Repository.UserRepository;
import com.jungmook.blog.dto.ResponseDto;
import com.jungmook.blog.dto.SignInDto;
import com.jungmook.blog.dto.SignInResponseDto;
import com.jungmook.blog.dto.SignUpDto;
import com.jungmook.blog.entity.UserEntity;
import com.jungmook.blog.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
     UserRepository userRepository;
    @Autowired
     TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userPassword);
        userEntity.setUserPassword(encodedPassword);

        try{
            //userRepository를 이용해서 데이터베이스에 Entity 저장!
            userRepository.save(userEntity);
        }catch (Exception e){
            ResponseDto.setFailed("DataBase 에러");
        }
        // 성공시 success 반환
        return ResponseDto.setSuccess("SignUp Success", null);
    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto){

        String userEmail = dto.getUserEmail();
        String userPassWord = dto.getUserPassword();

        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return ResponseDto.setFailed("Sign In Failed email"); // 잘못된 이메일
            if(!passwordEncoder.matches(userPassWord, userEntity.getUserPassword())) {
                System.out.println(userEntity.getUserPassword());
                return ResponseDto.setFailed("Sign In Failed password"); // 잘못된 패스워드
            }
        }catch (Exception e){
            return ResponseDto.setFailed("Database Error");
        }
        userEntity.setUserPassword("");
        String token = tokenProvider.create(userEmail);
        int exprTime = 3600000;

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);
        return ResponseDto.setSuccess("Sign In Success", signInResponseDto);
    }
}
