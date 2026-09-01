import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '/src/Login.css';
import axios from 'axios';
import BackHome from './BackHome';
import logo from "./images/logo.jpg";

function Login() {

  const [form, setForm] = useState({
    username: "",
    password: ""
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post(
        "http://localhost:8080/user/login",
        form
      );

      console.log("Login response:", res.data);

      if (res.data.message?.includes("Login success")) {

        localStorage.setItem(
          "user",
          JSON.stringify(res.data)
        );

        const previousPage = window.history.state?.usr;

        if (previousPage?.bus) {
          navigate("/booking", {
            state: {
              bus: previousPage.bus
            }
          });
        } else {
          navigate("/home");
        }

      } else {
        alert(res.data.message || "Invalid username or password");
      }

    } catch (error) {
      console.error("Login error:", error);

      alert(
        error.response?.data?.message ||
        "Login failed. Please check your username and password."
      );
    }
  };

  return (
    <div>
      <BackHome />

      <div className="login">
        <form onSubmit={handleSubmit}>

          <div>
            <img src={logo} alt="BusGo Logo" />
            <h1>BUSGO</h1>
          </div>

          <input
            type="text"
            placeholder="Username"
            name="username"
            value={form.username}
            onChange={handleChange}
            required
          />

          <input
            type="password"
            placeholder="Password"
            name="password"
            value={form.password}
            onChange={handleChange}
            required
          />

          <button type="submit">
            Login
          </button>

          <p>or</p>

          <Link to="/register">
            new user?
          </Link>

        </form>
      </div>
    </div>
  );
}

export default Login;