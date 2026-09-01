import React,{useState} from "react";
import {useLocation,useNavigate} from "react-router-dom";
import axios from "axios";
import "./Booking.css";
import BackHome from './BackHome'

const Booking=()=>{

    const {state}=useLocation();
    const navigate=useNavigate();

    const bus=state?.bus;
    const date=state?.date;
    const seatIds=state?.seatIds;

    const [form,setForm]=useState({
        passengerName:"",
        age:"",
        gender:"",
        mobileNumber:"",
        email:""
    });

    const change=(e)=>{

        setForm({
            ...form,
            [e.target.name]:e.target.value
        });

    };

    const confirmBooking=()=>{

        const user=JSON.parse(localStorage.getItem("user"));

        if(!user?.id){
            alert("User not found");
            return;
        }

        const data={

            userId:user.id,

            busId:bus.busId,

            travelDate:date,

            seatIds:seatIds,

            passengerName:form.passengerName,

            age:form.age,

            gender:form.gender,

            mobileNumber:form.mobileNumber,

            email:form.email
        };

     axios.post(
    "http://localhost:8080/api/bookings",
    data
)
        .then(()=>{

            alert("Booking successful");

            navigate("/mybooking");

        })
        .catch(err=>{

            console.log(err);

        });

    };

    return(
        <div>
            <BackHome></BackHome>
        <div className="booking">

            <h2>Booking</h2>

            <h3>Bus : {bus?.busNumber}</h3>

            <h3>Date : {date}</h3>

            <h3>
                Seats : {seatIds?.join(",")}
            </h3>

            <input
                name="passengerName"
                value={form.passengerName}
                onChange={change}
                placeholder="Passenger Name"
            />

            <input
                name="age"
                value={form.age}
                onChange={change}
                placeholder="Age"
                type="number"
            />

            <input
                name="gender"
                value={form.gender}
                onChange={change}
                placeholder="Gender"
            />

            <input
                name="mobileNumber"
                value={form.mobileNumber}
                onChange={change}
                placeholder="Mobile Number"
            />

            <input
                name="email"
                value={form.email}
                onChange={change}
                placeholder="Email"
            />

            <button onClick={confirmBooking}>
                Confirm Booking
            </button>

        </div>
        </div>
    );
};

export default Booking;