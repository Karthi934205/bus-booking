import React from 'react'
import './BackHome.css'
import { useNavigate } from 'react-router-dom'

function BackHome() {

    const navigate = useNavigate();

    return (
        <div className='backhome'>
        <button onClick={() => navigate("/home")}>
            Back to Home
        </button>
        </div>
    )
}

export default BackHome