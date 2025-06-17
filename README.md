
# MidSprint-SD12-API

A robust Spring Boot RESTful API built for the mid-course sprint of the DevOps and SDAT program. This service manages comprehensive airport data through standard CRUD operations and is designed for seamless integration into CI/CD pipelines.

## Description

This project delivers a production-ready API that enables clients to create, read, update, and delete airport resources. It leverages Spring Boot for rapid application development, Spring Data JPA for database interactions, and supports both MySQL and H2 for storage. With clear error handling and validation, it ensures robust data management and easy deployment.

## Features

### Airports

- **Create Airport**: Add new airports with details such as name, IATA code, city, and country.
- **Retrieve Airports**: Fetch all airports or query individual airport records by ID.
- **Update Airports**: Modify existing airport information safely.
- **Delete Airports**: Remove airport entries.
- **Airport Exception Handling**: Graceful handling of invalid airport requests and resource not found scenarios.

### Cities

- **Create City**: Add new cities with `name` and `country`.
- **Retrieve Cities**: Fetch all city records or query individual city by ID.
- **Update Cities**: Modify existing city details safely.
- **Delete Cities**: Remove city entries.

### Passengers

- **Create Passenger**: Register passengers with `firstName`, `lastName`, and `passportNumber`.
- **Retrieve Passengers**: List all passengers or retrieve one by ID.
- **Update Passengers**: Modify passenger personal details securely.
- **Delete Passengers**: Remove passenger records.
- **Passenger Data Checks**: Validate passport numbers and prevent duplicates.

### Aircraft

- **Create Aircraft**: Add aircraft entries with `model`, `manufacturer`, and `capacity`.
- **Retrieve Aircraft**: Fetch all aircraft or specific ones by ID.
- **Update Aircraft**: Update aircraft specifications and status.
- **Delete Aircraft**: Remove aircraft from the registry.
- **Aircraft Capacity Validation**: Ensure capacity values are within acceptable ranges.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Maven
- MySQL
- H2 Database (in-memory)

## Prerequisites

Before you begin, ensure you have the following installed:

- Java JDK 17 or above
- Maven 3.6+
- MySQL Server (optional: H2 is preconfigured for development)

## Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/Ndevine709/MidSprint-SD12-API.git
   ```

2. **Enter the project directory**

   ```bash
   cd MidSprint-SD12-API/Airport-API
   ```

3. **Build the project**

   ```bash
   mvn clean package
   ```

## Configuration

Rename `application.properties.example` to `application.properties` in `src/main/resources` and adjust the following settings:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
logging.level.org.springframework=INFO
server.port=8080
```

Switch to H2 by commenting out the MySQL properties and uncommenting H2 settings in the same file.

## Running the Application

Launch the API server:

```bash
mvn spring-boot:run
```

Access endpoints at `http://localhost:8080/api/airports`.

## API Endpoints

### Airports

- **GET** `/api/airports`  
  Retrieve a list of all airport records.

- **POST** `/api/airports`  
  Create a new airport. Provide JSON body with `name`, `code`, `city`, and `country`.

- **GET** `/api/airports/{id}`  
  Retrieve a specific airport by its unique ID.

- **PUT** `/api/airports/{id}`  
  Update details of an existing airport.

- **DELETE** `/api/airports/{id}`  
  Delete an airport record.

### Cities

- **GET** `/api/city`  
  Retrieve a list of all city records.

- **POST** `/api/city`  
  Create a new city. Provide JSON body with `name` and `country`.

- **GET** `/api/city/{id}`  
  Retrieve a specific city by its unique ID.

- **PUT** `/api/city/{id}`  
  Update details of an existing city.

- **DELETE** `/api/city/{id}`  
  Delete a city record

### Passengers

- **GET** `/api/passengers`  
  Retrieve a list of all passenger records.

- **POST** `/api/passenger`  
  Create a new passenger. Provide JSON body with `firstName`, `lastName`, and `passportNumber`.

- **GET** `/api/passengers/{id}`  
  Retrieve a specific passenger by its unique ID.

- **PUT** `/api/passengers/{id}`  
  Update details of an existing passenger.

- **DELETE** `/api/passengers/{id}`  
  Delete a passenger record.

### Aircraft

- **GET** `/api/aircrafts`  
  Retrieve a list of all aircraft records.

- **POST** `/api/aircraft`  
  Create a new aircraft. Provide JSON body with `model`, `manufacturer`, and `capacity`.

- **GET** `/api/aircraft/{id}`  
  Retrieve a specific aircraft by its unique ID.

- **PUT** `/api/aircraft/{id}`  
  Update details of an existing aircraft.

- **DELETE** `/api/aircraft/{id}`  
  Delete an aircraft record.

## Contributing

We welcome contributions! To submit improvements or fixes:

1. Fork the repository.  
2. Create a feature branch: `git checkout -b feature/YourFeatureName`.  
3. Commit your changes: `git commit -m 'Add some feature'`.  
4. Push to the branch: `git push origin feature/YourFeatureName`.  
5. Open a Pull Request and describe your changes.

## Authors

- **Noah Devine** - [@Ndevine709](https://github.com/Ndevine709)  
- **Laura Wiseman** - [@lauraawiseman](https://github.com/lauraawiseman)
- **Chris M** - [@ChrisM709](https://github.com/ChrisM709)  
