import React from 'react'
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom'
import '/src/Register.css'
import axios from 'axios';
import BackHome from './BackHome';
import logo from "./images/logo.jpg";

function Register() {

  const [form,setForm]=useState({
    username:"",
    mobile:"",
    password:""
  })

  const navigate=useNavigate();

  const handleChange=(e)=>{
    setForm({...form,[e.target.name]:e.target.value})
  }

  const handleSubmit=async(e)=>{
    e.preventDefault();
    const res=await axios.post(
  "http://localhost:8080/user/register",
  form
);
    alert(res.data.message);
    if(res.data.message.includes("Registered successfully"))
    {
    localStorage.setItem("user", JSON.stringify(res.data));
    const previousPage = window.history.state?.usr;

      if(previousPage?.bus)
      {
          navigate("/booking", {
              state:{
                  bus: previousPage.bus
              }
          });
      }
      else
      {
          navigate("/home");
      }

    }

  }

  return (
    <div>
      <BackHome></BackHome>
    <div className='register'>
        <form action="" onSubmit={handleSubmit}>
            <div>
                <img src={logo} alt="" />
                <h1>BUSGO</h1>
            </div>
            <input type="text" name='username' placeholder='UserName' required onChange={handleChange}/>
            <input type="tel" name='mobile' placeholder='Mobile Number' required onChange={handleChange}/>
            <input type="password" name='password' placeholder='Password' required onChange={handleChange}/>
            <button type="submit">Register</button>
            <p>or</p>
            <Link to='/login'>Already have an account?</Link>
        </form>
    </div>
    </div>
  )
}

export default Register