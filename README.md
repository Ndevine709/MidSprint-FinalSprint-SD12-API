# Airport Arrivals & Departures API

A robust Spring Boot RESTful API built for the **final project** of the DevOps and SDAT program. This service manages comprehensive airport data including **flights, arrivals, and departures** through standard CRUD operations and is designed for seamless integration into CI/CD pipelines.

## Description

This project delivers a production-ready API that enables comprehensive airport management through a sophisticated data model. It leverages Spring Boot for rapid application development, Spring Data JPA for database interactions, and supports both MySQL and H2 for storage. With clear error handling and validation, it ensures robust data management and easy deployment.

## Features

### Airports

- **Create Airport**: Add new airports with details such as name, IATA code, city, and country.
- **Retrieve Airports**: Fetch all airports or query individual airport records by ID.
- **Update Airports**: Modify existing airport information safely.
- **Delete Airports**: Remove airport entries.
- **Airport Exception Handling**: Graceful handling of invalid airport requests and resource not found scenarios.

### Cities

- **Create City**: Add new cities with `name`, `state`, and `population`.
- **Retrieve Cities**: Fetch all city records or query individual city by ID.
- **Update Cities**: Modify existing city details safely.
- **Delete Cities**: Remove city entries.

### Passengers

- **Create Passenger**: Register passengers with `firstName`, `lastName` `phoneNumber`, and `birthday`.
- **Retrieve Passengers**: List all passengers or retrieve one by ID.
- **Update Passengers**: Modify passenger personal details securely.
- **Delete Passengers**: Remove passenger records.

### Aircraft

- **Create Aircraft**: Add aircraft entries with `tailNumber`, `model`, and `capacity`.
- **Retrieve Aircraft**: Fetch all aircraft or specific ones by ID.
- **Update Aircraft**: Update aircraft specifications and status.
- **Delete Aircraft**: Remove aircraft from the registry.

### Airlines

- **Create Airline**: Add new airlines with `name` and `code`.
- **Retrieve Airlines**: Fetch all airline records or query individual airline by ID.
- **Update Airlines**: Modify existing airline details safely.
- **Delete Airlines**: Remove airline entries.

### Gates

- **Create Gate**: Add new gates with gate number, airport, and departure/arrival designation.
- **Retrieve Gates**: Fetch all gates or filter by airport and type (departure/arrival).
- **Update Gates**: Modify gate information and aircraft assignments.
- **Delete Gates**: Remove gate entries.
- **Aircraft Assignment**: Assign aircraft to specific gates for departures and arrivals.

### Flights 

- **Create Flight**: Add new flights with airline, aircraft, airports, and scheduled times.
- **Retrieve Flights**: Fetch all flights or query by airport, airline, status, or ID.
- **Update Flights**: Modify flight details and status (SCHEDULED, BOARDING, DEPARTED, ARRIVED, DELAYED, CANCELLED).
- **Delete Flights**: Remove flight records.
- **Arrivals/Departures**: Filter flights by airport for arrivals and departures.
- **Status Management**: Track and update flight status with delay reasons.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Maven
- MySQL
- JUnit 5 & Mockito for testing

## Prerequisites

Before you begin, ensure you have the following installed:

- Java JDK 17 or above
- Maven 3.6+
- MySQL Server

## Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/Ndevine709/MidSprint-FinalSprint-SD12-API.git
   ```

2. **Enter the project directory**

   ```bash
   cd MidSprint-FinalSprint-SD12-API/Airport-API
   ```

3. **Build the project**

   ```bash
   mvn clean package
   ```

## Running the Application

Launch the API server:

- Run the RestServiceApplication file through IntelliJ

Access endpoints at `http://localhost:8080`.

## API Endpoints

### Airports

- **GET** `/airport`  
  Retrieve a list of all airport records.

- **POST** `/airport`  
  Create a new airport. Provide JSON body with `name`, `code`, and `city`.

- **GET** `/airport/{id}`  
  Retrieve a specific airport by its unique ID.

- **PUT** `/airport/{id}`  
  Update details of an existing airport.

- **DELETE** `/airport/{id}`  
  Delete an airport record.

- **GET** `/airport/{airportId}/aircraft`  
  Retrieve all aircraft associated with a specific airport.

### Cities

- **GET** `/city`  
  Retrieve a list of all city records.

- **POST** `/city`  
  Create a new city. Provide JSON body with `name`, `state`, and `population`.

- **GET** `/city/{id}`  
  Retrieve a specific city by its unique ID.

- **PUT** `/city/{id}`  
  Update details of an existing city.

- **DELETE** `/city/{id}`  
  Delete a city record.

### Passengers

- **GET** `/passengers`  
  Retrieve a list of all passenger records.

- **POST** `/passenger`  
  Create a new passenger. Provide JSON body with `firstName`, `lastName`, and `birthday`, and `phoneNumber`.

- **GET** `/passengers/{id}`  
  Retrieve a specific passenger by its unique ID.

- **PUT** `/passengers/{id}`  
  Update details of an existing passenger.

- **DELETE** `/passengers/{id}`  
  Delete a passenger record.

### Aircraft

- **GET** `/aircraft`  
  Retrieve a list of all aircraft records.

- **POST** `/aircraft`  
  Create a new aircraft. Provide JSON body with `tailNumber`, `model`, and `capacity`.

- **GET** `/aircraft/{id}`  
  Retrieve a specific aircraft by its unique ID.

- **PUT** `/aircraft/{id}`  
  Update details of an existing aircraft.

- **DELETE** `/aircraft/{id}`  
  Delete an aircraft record.

- **PUT** `/aircraft/{aircraftId}/airport/{airportId}`  
  Assign an aircraft to a specific airport.

### Airlines

- **GET** `/airline`  
  Retrieve a list of all airline records.

- **POST** `/airline`  
  Create a new airline. Provide JSON body with `name` and `code`.

- **GET** `/airline/{id}`  
  Retrieve a specific airline by its unique ID.

- **PUT** `/airline/{id}`  
  Update details of an existing airline.

- **DELETE** `/airline/{id}`  
  Delete an airline record.

### Gates

- **GET** `/gates`  
  Retrieve a list of all gate records.

- **POST** `/gates`  
  Create a new gate. Provide JSON body with `gateNumber`, `airport`, and `isDepartureGate`.

- **GET** `/gates/{id}`  
  Retrieve a specific gate by its unique ID.

- **PUT** `/gates/{id}`  
  Update details of an existing gate.

- **DELETE** `/gates/{id}`  
  Delete a gate record.

- **GET** `/gates/airport/{airportId}/departures`  
  Retrieve all departure gates for a specific airport.

- **GET** `/gates/airport/{airportId}/arrivals`  
  Retrieve all arrival gates for a specific airport.

### Flights

- **GET** `/flights`  
  Retrieve a list of all flight records.

- **POST** `/flights`  
  Create a new flight. Provide JSON body with flight details.

- **GET** `/flights/{id}`  
  Retrieve a specific flight by its unique ID.

- **PUT** `/flights/{id}`  
  Update details of an existing flight.

- **DELETE** `/flights/{id}`  
  Delete a flight record.

- **GET** `/flights/airport/{airportId}/departures`  
  Retrieve all departing flights from a specific airport.

- **GET** `/flights/airport/{airportId}/arrivals`  
  Retrieve all arriving flights to a specific airport.

- **GET** `/flights/airline/{airlineId}`  
  Retrieve all flights for a specific airline.

- **PUT** `/flights/{id}/status`  
  Update the status of a specific flight.

## Contributing

We welcome contributions! To submit improvements or fixes:

1. Fork the repository.  
2. Create a feature branch: `git checkout -b feature/YourFeatureName`.  
3. Commit your changes: `git commit -m 'Add some feature'`.  
4. Push to the branch: `git push origin feature/YourFeatureName`.  
5. Open a Pull Request and describe your changes.

## Key Relationships

- **Airports ↔ Flights**: Manage arrivals and departures
- **Aircraft ↔ Gates**: Assign aircraft to departure/arrival gates  
- **Airlines ↔ Aircraft**: Track aircraft ownership
- **Cities ↔ Airports**: Organize airports by location

## Authors

- **Noah Devine** - [@Ndevine709](https://github.com/Ndevine709)  
- **Laura Wiseman** - [@lauraawiseman](https://github.com/lauraawiseman)
- **Chris M** - [@ChrisM709](https://github.com/ChrisM709)  
