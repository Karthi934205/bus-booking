import React,{useEffect,useState} from "react";
import axios from "axios";
import "./Mybooking.css";
import BackHome from "./BackHome";

const MyBooking=()=>{

    const [bookings,setBookings]=useState([]);

    useEffect(()=>{
        loadBookings();
    },[]);
const loadBookings = () => {
  const user = JSON.parse(localStorage.getItem("user"));

  if (!user?.id) {
    console.log("User id not found");
    return;
  }

  axios
    .get(`http://localhost:8080/api/bookings/user/${user.id}`)
    .then((res) => {
      setBookings(res.data);
    })
    .catch((err) => {
      console.log("Load booking error:", err);
    });
};



   const cancelBooking = (id) => {
  axios
    .delete(`http://localhost:8080/api/bookings/${id}`)
    .then(() => {
      alert("Booking cancelled");
      loadBookings();
    })
    .catch((err) => {
      console.log("Cancel booking error:", err);
    });
};

    return(
        <div>
            <BackHome></BackHome>
        <div className="my-booking">

            <h2>My Bookings</h2>

            {
                bookings.length===0 ?

                <h3>No bookings found</h3>

                :

                bookings.map(booking=>(

                    <div className="booking-card" key={booking.bookingId}>

                        <h3>
                            Bus : {booking.busNo}
                        </h3>

                        <p>
                            Route : {booking.source} - {booking.destination}
                        </p>

                        <p>
                            Passenger : {booking.passengerName}
                        </p>

                        <p>
                            Date : {booking.bookingDate}
                        </p>

                        <p>
                            Seats Booked : {booking.seatsBooked}
                        </p>

                        <p>
                            Status : {booking.status}
                        </p>

                        {
                            booking.status !== "Cancelled" &&
                            <button onClick={()=>cancelBooking(booking.bookingId)}>
                                Cancel Booking
                            </button>
                        }

                    </div>

                ))
            }

        </div>
        </div>
    );
};

export default MyBooking;