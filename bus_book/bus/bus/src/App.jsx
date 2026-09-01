import React from 'react'
import Search from './Search'
import Login from './Login'
import Register from './Register'
import Home from './Home'
import { Routes, Route } from "react-router-dom";
import Booking from "./Booking";
import Mybooking from './Mybooking'
import Profiledet from "./Profiledet"
import SeatSelection from "./SeatSelection";

function App() {
  return (
    <div>
      <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/home" element={<Home />} />
      <Route path="/search" element={<Search showBackHome={true}/>}/>
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/booking" element={<Booking/>}/>
      <Route path="/mybooking" element={<Mybooking/>}/>
      <Route path='/profile' element={<Profiledet/>}/>
      <Route path="/seat-selection" element={<SeatSelection />}/>
    </Routes>
    </div>
  )
}

export default App