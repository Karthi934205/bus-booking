import React from 'react'
import { useNavigate } from 'react-router-dom'

const Profiledet = () => {

    const navigate = useNavigate();

    const user = JSON.parse(localStorage.getItem("user"));

    const logout = () => {
        localStorage.removeItem("user");
        navigate("/home");
    }

    return (
        <div className='profilecard'>

            <h3>{user?.username}</h3>

            <h3>{user?.mobile}</h3>

            <button onClick={logout}>
                Logout
            </button>

        </div>
    )
}

export default Profiledet;