import {Button, Card, CardActions, CardContent, CardHeader, TextField, Typography} from "@mui/material";
import {Box} from "@mui/system";
import axios from "axios";
import React, { useState } from "react";
import { useCookies } from "react-cookie";
import { useUserStore } from "../../../stores";
import { signInApi } from "../../../apis";

interface Props{
    setAuthView : (authView : boolean) => void,
}

export default function SignIn(props: Props){

    const [email, setEmail] = useState<String>('');
    const [password, setPassword] = useState<String>('');
    const [cookies, setCoockies] = useCookies();

    const {user, setUser} = useUserStore();


    const { setAuthView } = props

    const signInhandler = async () =>{
        if(email.length === 0 || password.length ===0){ 
            alert("이메일과 비밀번호를 입력하세요.")
            return ;
        }

        const data = {
            userEmail: email,
            userPassword: password
        }

        const signInResponse = await signInApi(data);

        if(!signInResponse) {
            alert("로그인에 실패했습니다.")
            return;
        }

        if(!signInResponse.result){
            alert("로그인에 실패했습니다.")
            return;            
        }
        const {token, exprTime, user} = signInResponse.data;
        const expires = new Date();
        expires.setMilliseconds(expires.getMilliseconds + exprTime);

        setCoockies('token',token, {expires});
        setUser(user);
        
    };
    return(
        <Card sx={{minWidth : 275, maxWidth: "50vw" , padding: 5}}>
            <Box>
                <Typography variant="h5">로그인</Typography>
            </Box>
                <Box height={'50vh'}>
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
                <Box component='div'>
                    <Button fullWidth variant="contained" onClick={() =>(signInhandler())}>로그인</Button>
                </Box>
                <Box component='div' display='flex' mt={2}>
                <Typography>신규 사용자 이신가요?</Typography>
                <Typography fontWeight={800} mr={1} onClick ={() => setAuthView(true)}>회원가입</Typography>
                </Box>
        </Card>
    )
}