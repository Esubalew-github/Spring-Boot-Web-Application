# Spring Boot Web Application #
This repository contains a simplified version of Spring Boot web application made for the purpose of assessment.
## Description of the assessment
The task is to write a production-ready code that retrieves the following information given 3 CSV files [which contain data for countries, airports and runway information]:
- Runways for each airport given a country code or country name [country name is chosen as input 
  for this application].
- Top 10 countries with the highest number of airports.
- Support retrieving the information given a partial/fuzzy country code/name as input parameter, e.g. entering 'zimb' 
  will result in 'Zimbabwe'.
  
A simple Spring Boot web application, which uses Hibernate JPA implementation and PostgreSQL database, is created to achieve the above goal.
## Tools and Technologies used
- JDK
- Spring Boot
- PostgreSQL 
- Hibernate JPA implementation
- Maven
- Thymeleaf
- IntelliJ IDEA


## How to run the application
1. Clone the application

https://github.com/Esubalew-github/assessment

2. Create a database. For PostgreSQL database use 

```
CREATE DATABASE test_db
```
In the above command, test_db is a name for the database as an example.

3. Run these sql script files and create tables. 

```
CREATE TABLE IF NOT EXISTS countries (
  id              INTEGER CONSTRAINT PK_COUNTRIES PRIMARY KEY,
  code            VARCHAR(10) NOT NULL,
  name            VARCHAR(255) NOT NULL,
  continent       VARCHAR(10),
  wikipedia_link  VARCHAR(255),
  keywords        VARCHAR(100),
  CONSTRAINT UNQ_CODE UNIQUE(code),
  CONSTRAINT UNQ_NAME UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS airports (
  id                    INTEGER CONSTRAINT PK_AIRPORTS PRIMARY KEY,
  ident                 VARCHAR(10),
  type                  VARCHAR(100),
  name                  VARCHAR(255) NOT NULL,
  latitude_deg          FLOAT8,
  longitude_deg         FLOAT8,
  elevation_ft          INTEGER,
  continent             VARCHAR(10),
  iso_country           VARCHAR(10),
  iso_region            VARCHAR(10),
  municipality          VARCHAR(100),
  scheduled_service     VARCHAR(100),
  gps_code              VARCHAR(100),
  iata_code             VARCHAR(100),
  local_code            VARCHAR(100),
  home_link             VARCHAR(255),
  wikipedia_link        VARCHAR(255),
  keywords              VARCHAR(255),
  FOREIGN KEY (iso_country) REFERENCES countries(code)
);

CREATE TABLE IF NOT EXISTS runways (
  id                        INTEGER CONSTRAINT PK_RUNWAYS PRIMARY KEY,
  airport_ref               INTEGER,
  airport_ident             VARCHAR(100),
  length_ft                 INTEGER,
  width_ft                  INTEGER,
  surface                   VARCHAR(100),
  lighted                   BOOL,
  closed                    BOOL,
  le_ident                  VARCHAR(10),
  le_latitude_deg           FLOAT8,
  le_longitude_deg          FLOAT8,
  le_elevation_ft           INTEGER,
  le_heading_degT           FLOAT8,
  le_displaced_threshold_ft INTEGER,
  he_ident                  VARCHAR(100),
  he_latitude_deg           FLOAT8,
  he_longitude_deg          FLOAT8,
  he_elevation_ft           INTEGER,
  he_heading_degT           FLOAT8,
  he_displaced_threshold_ft INTEGER,
  FOREIGN KEY (airport_ref) REFERENCES airports(id)
);
```
4. Upload the CSV data with a single SQL command per a table. For PostgreSQL database use  
```
COPY countries FROM '<file_path>' DELIMITER ',' CSV HEADER;
```
```
COPY airports FROM '<file_path>' DELIMITER ',' CSV HEADER;
```
```
COPY runways FROM '<file_path>' DELIMITER ',' CSV HEADER;
```
5. Change url, username and password as per your installation

- open src/main/resources/application.properties file.

- change spring.datasource.url, spring.datasource.username and spring.datasource.password as per your installation.

6. Run the application

- The Spring Boot Maven plugin includes a run goal which can be used to quickly compile and run the application. Hence, you can run the spring boot app by typing the following command:

```
mvn spring-boot:run
```

- If you use the Spring Boot Maven plugins to create an executable jar you can run the application using java -jar.
```
java -jar target/assessment-0.0.1-SNAPSHOT.jar
```
- You can also run the application from your IDE as a simple Java application, however, first you will need to import the project. Import steps will vary depending on your IDE and build system. Most IDEs can import Maven projects directly. If you can’t directly import it into your IDE, you may be able to generate IDE metadata using a build plugin. Maven includes plugins for Eclipse and IDEA.

The server will start on port 8080.




