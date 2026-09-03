import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Profile from './Profile';
import '/src/Nav.css';
import Mybooking from './Mybooking';
import logo from "./images/logo.jpg";

function Nav() {

  const [user, setUser] = useState(
    JSON.parse(localStorage.getItem("user"))
  );

  return (
    <div className='Nav'> 

        <section className='section'>
            <img src={logo} alt="" />
            <h1>bus book</h1>
        </section>


        <section className='section1'>

            <Link to='/home'>Home</Link>

            <Link to='/search'>Search Bus</Link>


            {
              user ? (
                <div className='after'>
                <Profile 
                    user={user}
                    setUser={setUser}
                />
                <Link to='/mybooking'>My Bookings</Link>
                </div>
              ) : (
                <>
                  <Link to="/login">Login/register</Link>
                </>
              )
            }

        </section>

    </div>
  )
}

export default Nav;