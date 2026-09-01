import React from "react";
import "./Searchres.css";
import { useNavigate } from "react-router-dom";

function SearchRes({ buses }) {

    const navigate = useNavigate();

    function handleBooking(bus) {

        const user = JSON.parse(localStorage.getItem("user"));

        if (!user) {
            alert("Please login or register before booking");
            navigate("/login");
            return;
        }

        navigate("/seat-selection", {
            state: {
                bus: bus
            }
        });
    }

    return (
        <div className="container">

            {
                buses.length === 0 ?

                <h3>No Buses Available</h3>

                :

                buses.map((e) => (

                    <div className="buscard" key={e.busId}>

                        <h2>{e.busNumber}</h2>

                        <div className="route">
                            <span>{e.source}</span>
                            ➡️
                            <span>{e.destination}</span>
                        </div>

                        <p>
                            Departure:
                            <b>{e.departureTime}</b>
                        </p>

                        <p>
                            Arrival:
                            <b>{e.arrivalTime}</b>
                        </p>

                        <p>
                            Available Seats:
                            <b>{e.seatsAvailable}</b>
                        </p>

                        <button onClick={() => handleBooking(e)}>
                            Book Now
                        </button>

                    </div>

                ))
            }

        </div>
    );
}

export default SearchRes