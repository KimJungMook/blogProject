import React, { useEffect, useState } from "react";
import Navigation from "../../Navigation";
import { Box } from "@mui/material";
import SignUp from "../../Authentication/SignUp";
import SignIn from "../../Authentication/SignIn";
import Authentication from "../../Authentication";
import BoardMain from "../../BlogMain";
import { useUserStore } from "../../../stores";
import { useCookies } from "react-cookie";
import axios from "axios";

export default function Main(){
  const [boardResponse, setBoardResponse] = useState<string>('');
  const {user} = useUserStore();
  const [cookies] = useCookies();

  const getBoard = async (token: string) =>{
    await axios.get('http://localhost:4000/api/board/',{
      headers: {
        Authorization: `Bearer ${token}`
      }
    }).then((response) =>{
      setBoardResponse(response.data);
    }).catch((e)=> '');
  }
  useEffect(()=>{
    const token = cookies.token
    if(token){
      getBoard(token);
    }else{
      setBoardResponse('');
    }
  },[cookies.token])

    return(
    <>
      <Navigation/>
      {boardResponse ?  <BoardMain/> : <Authentication/>}
    </>
    )
}