import React from "react";
import Navigation from "../../Navigation";
import { Box } from "@mui/material";
import SignUp from "../../Authentication/SignUp";
import SignIn from "../../Authentication/SignIn";
import Authentication from "../../Authentication";

export default function Main(){
    return(
    <>
      <Navigation/>
      <Authentication/>
    </>
    )
}