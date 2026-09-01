import React from "react";
import "./SeatLayout.css";

const SeatLayout = ({ seats, selectedSeats, setSelectedSeats }) => {

    const selectSeat = (seat) => {
        if (!seat.available) return;

        if (selectedSeats.includes(seat.seatId)) {
            setSelectedSeats(
                selectedSeats.filter(id => id !== seat.seatId)
            );
        } else {
            setSelectedSeats([
                ...selectedSeats,
                seat.seatId
            ]);
        }
    };

    return (
        <div className="seat-container">
            <h3>Driver</h3>

            <div className="seat-grid">
                {
                    seats.map(seat =>
                        <button
                            key={seat.seatId}
                            disabled={!seat.available}
                            onClick={() => selectSeat(seat)}
                            className={
                                !seat.available
                                ? "seat booked"
                                : selectedSeats.includes(seat.seatId)
                                ? "seat selected"
                                : "seat available"
                            }
                        >
                            {seat.seatNumber}
                        </button>
                    )
                }
            </div>
        </div>
    );
};

export default SeatLayout;