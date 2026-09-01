import React, { useState } from 'react'
import { Link } from 'react-router-dom';
import './Profile.css'

function Profile({user, setUser}) {

    const [show, setShow] = useState(false)

    function logout()
    {
        localStorage.removeItem("user");
        setUser(null);
    }

    return (
        <div className='profile'>

            <div className='profileicon' onClick={() => setShow(true)}>👤︎</div>

            {
                show &&
                <div className='profilecard'>
                    <button onClick={()=>setShow(false)}>close</button>
                    <h3>{user.username}</h3>
                    <h3>{user.mobile}</h3>

                    <button onClick={logout}>
                        Logout
                    </button>
                </div>
            }

        </div>
    )
}

export default Profile;