import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import SignUp from './views/Authentication/SignUp';
import Authentication from './views/Authentication';

function App() {
  // const [connection, setConnection] = useState<string>('');

  // const connectionTest = () =>{
  //   axios.get('http://localhost:4000/').then((response) =>{
  //     setConnection(response.data);
  //   }).catch((error) =>{
  //     setConnection(error.message);
  //   })
  // }
//
  useEffect(()=>{
    // connectionTest();
  },[])
  return (
    <Authentication/>
  );
}

export default App;
