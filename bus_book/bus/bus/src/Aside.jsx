import './Aside.css'

import React from 'react'
import credit from "./images/credit-card.png";
import booking from "./images/booking.png";
import time from "./images/time.png";

function Aside() {
  return (
    <div className='aside'> 
        <section>
            <img src={booking} alt="" />
            <h2>Easy Booking</h2>
            <p>Find and book your seat in just a few taps.</p>
        </section>
        <section>
            <img src={credit} alt="" />
            <h2>Secured Payment</h2>
            <p>Your bookings and details are protected end to end.</p>
        </section>
        <section>
            <img src={time} alt="" />
            <h2>Real-Time Seat</h2>
            <p>See live seats availability across every routes.</p>
        </section>
    </div>
  )
}

export default Aside