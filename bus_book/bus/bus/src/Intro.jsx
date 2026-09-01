import React from 'react'
import './Intro.css'
import intro from "./images/intro.jpg";

function Intro() {
  return (
    <div className='Intro'>
        <section className='right'>
            <h3><span>Travel Made</span></h3>
            <h1> BUS TICKET</h1>
            <p>Search thousands of routes, compare buses, and reserve your seat in seconds. Your next trip is just a few click</p>
        </section>
        <section className='left'>
            <img src={intro} alt="" />
        </section>
    </div>
  )
}

export default Intro