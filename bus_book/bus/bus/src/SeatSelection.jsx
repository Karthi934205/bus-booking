import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import SeatLayout from "./SeatLayout";
import './SeatSelection.css'

const SeatSelection = () => {
    const { state } = useLocation();
    const navigate = useNavigate();
    const bus = state?.bus;

    const [date, setDate] = useState("");
    const [seats, setSeats] = useState([]);
    const [selectedSeats, setSelectedSeats] = useState([]);

    if (!bus) {
        navigate("/search");
        return null;
    }

    const fetchSeats = () => {
        if (!date) {
            alert("Select travel date");
            return;
        }
axios.get  (`http://localhost:8080/api/seats/bus/${bus.busId}?date=${date}`)
            .then(res => setSeats(res.data))
            .catch(err => console.log(err));
    };

    const continueBooking = () => {
        if (selectedSeats.length === 0) {
            alert("Select seats");
            return;
        }

        navigate("/booking", {
            state: {
                bus,
                date,
                seatIds: selectedSeats
            }
        });
    };

    return (
        <div className="seat-selection">
            <h2>Select Seats</h2>

            <h3>{bus.busNumber}</h3>
            <p>{bus.source} → {bus.destination}</p>

            <input
                type="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
            />

            <button onClick={fetchSeats}>
                Check Seats
            </button>

            {
                seats.length > 0 &&
                <SeatLayout
                    seats={seats}
                    selectedSeats={selectedSeats}
                    setSelectedSeats={setSelectedSeats}
                />
            }

            {
                selectedSeats.length > 0 &&
                <>
                    <h3>
                        Selected Seats: {selectedSeats.join(",")}
                    </h3>

                    <button onClick={continueBooking}>
                        Continue Booking
                    </button>
                </>
            }
        </div>
    );
};

export default SeatSelection;