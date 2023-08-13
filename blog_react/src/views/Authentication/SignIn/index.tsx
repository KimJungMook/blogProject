import {Button, Card, CardActions, CardContent, TextField} from "@mui/material";
import {Box} from "@mui/system";
import axios from "axios";
import React, { useState } from "react";
import { useCookies } from "react-cookie";
import { useUserStore } from "../../../stores";

export default function SignIn(){
    const [email, setEmail] = useState<String>('');
    const [password, setPassword] = useState<String>('');
    const [cookies, setCoockies] = useCookies();

    const {user, setUser} = useUserStore();

    const signInhandler = () =>{
        if(email.length === 0 || password.length ===0){ 
            alert("이메일과 비밀번호를 입력하세요.")
            return ;
        }

        const data = {
            userEmail: email,
            userPassword: password
        }
        axios
            .post("http://localhost:4000/api/auth/signIn", data)
            .then((response) =>{
                const responseData = response.data;
                if(!responseData.result){
                    alert("로그인에 실패했습니다.")
                    return;
                }
                console.log(responseData)
                const {token, exprTime, user} = responseData.data;
                const expires = new Date();
                expires.setMilliseconds(expires.getMilliseconds + exprTime);

                setCoockies('token',token, {expires});
                setUser(user);
                alert(cookies.token);
            })
            .catch((e) =>{
                alert("로그인 실패");
            })

    }
    return(
        <Card sx={{minWidth : 275, maxWidth: "50vw"}}>
            {/* {user != null && (<>{user.userNickname}</>)} */}
            <CardContent>
                <Box>
                    <TextField
                        fullWidth
                        label="이메일"
                        type="email"
                        variant="standard"
                        onChange={(e) => {setEmail(e.target.value)}}
                    />
                    <TextField
                        fullWidth
                        label="비밀번호"
                        type="password"
                        variant="standard"
                        onChange={(e) => {setPassword(e.target.value)}}
                    />
                </Box>
            </CardContent>
            <CardActions>
                <Button fullWidth variant="contained" onClick={() =>(signInhandler())}>로그인</Button>
            </CardActions>
        </Card>
    )
}