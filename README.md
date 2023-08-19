# Currency Conversion
Application to convert an amount from one currency to another.

# Running the project

##  Prerequisites

1. JDK 11 configured
2. Maven configured

## Commands

1. Run the command `mvn clean compile package`
2. Move to `target` directory and run the command `java -jar currency-conversion-0.0.1-SNAPSHOT.jar`

## Testing if application is running

1. Run the command `curl --request GET --url http://localhost:8080/currency-conversion/healthcheck`. If the application
is running correctly, you should receive the message `OK` as return