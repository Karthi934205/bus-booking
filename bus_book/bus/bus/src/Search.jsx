import React, { useState, useEffect } from "react";
import SearchRes from "./SearchRes";
import "./Search.css";
import BackHome from "./BackHome";

function Search({ showBackHome = false }) {

  const [state, setState] = useState([]);
  const [stage, setStage] = useState(false);

  const [locations, setLocations] = useState([]);
  const [sourceInput, setSourceInput] = useState("");
  const [destinationInput, setDestinationInput] = useState("");

  const [showSourceList, setShowSourceList] = useState(false);
  const [showDestinationList, setShowDestinationList] = useState(false);

  // Get all bus locations
  useEffect(() => {

    const fetchLocations = async () => {

      try {

        const response = await fetch(
          "http://localhost:8080/api/buses/"
        );

        if (!response.ok) {
          throw new Error(`HTTP Error: ${response.status}`);
        }

        const data = await response.json();

        console.log("All Bus Data:", data);

        const places = [];

        data.forEach((bus) => {

          if (bus.source) {
            places.push(bus.source);
          }

          if (bus.destination) {
            places.push(bus.destination);
          }

        });

        setLocations([...new Set(places)]);

      } catch (error) {

        console.error("Location Error:", error);

      }

    };

    fetchLocations();

  }, []);

  // Filter locations
  const filteredLocations = (value) => {

    return locations.filter((place) =>
      place.toLowerCase().includes(value.toLowerCase())
    );

  };

  // Search buses
  const handleSubmit = async (e) => {

    e.preventDefault();

    if (!sourceInput.trim() || !destinationInput.trim()) {
      alert("Please select From and To");
      return;
    }

    if (
      sourceInput.trim().toLowerCase() ===
      destinationInput.trim().toLowerCase()
    ) {
      alert("Source and destination cannot be same");
      return;
    }

    console.log("Searching From:", sourceInput);
    console.log("Searching To:", destinationInput);

    try {

      const params = new URLSearchParams();

      params.append("source", sourceInput.trim());
      params.append("destination", destinationInput.trim());
      params.append("seats", "1");

      const url =
        `http://localhost:8080/api/buses/search?${params.toString()}`;

      console.log("Search URL:", url);

      const response = await fetch(url);

      if (!response.ok) {

        throw new Error(
          `HTTP Error: ${response.status}`
        );

      }

      const data = await response.json();

      console.log("Bus Search Result:", data);

      if (!data || data.length === 0) {

        setState([]);
        setStage(true);

        alert("No available buses for this route");

        return;

      }

      setState(data);
      setStage(true);

    } catch (error) {

      console.error("Bus Search Error:", error);

      setState([]);
      setStage(true);

      alert("Unable to search buses");

    }

  };

  return (
    <div>

      {showBackHome && <BackHome />}

      <div className="search">

        <form onSubmit={handleSubmit}>

          {/* FROM */}

          <section className="autocomplete">

            <label>From</label>

            <input
              type="text"
              name="source"
              placeholder="📍 Source"
              value={sourceInput}
              onChange={(e) => {
                setSourceInput(e.target.value);
                setShowSourceList(true);
              }}
              onFocus={() => setShowSourceList(true)}
              required
            />

            {showSourceList && (

              <div className="suggestions">

                <button
                  type="button"
                  className="close-suggestion"
                  onClick={() => setShowSourceList(false)}
                >
                  ✕
                </button>

                {filteredLocations(sourceInput).map(
                  (place, index) => (

                    <div
                      key={index}
                      onClick={() => {
                        setSourceInput(place);
                        setShowSourceList(false);
                      }}
                    >
                      {place}
                    </div>

                  )
                )}

              </div>

            )}

          </section>


          {/* TO */}

          <section className="autocomplete">

            <label>To</label>

            <input
              type="text"
              name="destination"
              placeholder="📍 Destination"
              value={destinationInput}
              onChange={(e) => {
                setDestinationInput(e.target.value);
                setShowDestinationList(true);
              }}
              onFocus={() => setShowDestinationList(true)}
              required
            />

            {showDestinationList && (

              <div className="suggestions">

                <button
                  type="button"
                  className="close-suggestion"
                  onClick={() =>
                    setShowDestinationList(false)
                  }
                >
                  ✕
                </button>

                {filteredLocations(destinationInput).map(
                  (place, index) => (

                    <div
                      key={index}
                      onClick={() => {
                        setDestinationInput(place);
                        setShowDestinationList(false);
                      }}
                    >
                      {place}
                    </div>

                  )
                )}

              </div>

            )}

          </section>


          {/* SEARCH BUTTON */}

          <section className="searchbut">

            <button type="submit">
              🔎 Search
            </button>

          </section>

        </form>

      </div>


      {/* RESULTS */}

      {stage && state.length > 0 && (
        <SearchRes buses={state} />
      )}

      {stage && state.length === 0 && (
        <h3 style={{ textAlign: "center" }}>
          No available buses
        </h3>
      )}

    </div>
  );
}

export default Search;