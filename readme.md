# Musala API server

This project done using: Java (OpenJDK 8), Spring Boot, Hibernate And H2 in-memory DB.

### Functionality includes:

#### Devices:

1: Add a new device to a gateway. 2: Remove a device from gateway. 3: Update device (Included in API but not in UI). 4:
Get device by ID.

#### Gateways:

1: Add a new gateway. 2: Remove a gateway. 3: Get gateways list. 4: Get gateway by UUID.

## STEPS to run the project

#### 1. Clone this repository

Using ``` git clone https://github.com/MuhammedSabry/musala-client.git ``` Or downloading it as a ZIP

#### 2. Required dependencies

Your PC must have [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) installed
and [Maven](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) installed and added to
your [environment variables](https://www.mkyong.com/maven/how-to-install-maven-in-windows/).

#### 3. Run maven build

``` mvn install ```

#### 4. Run development server locally

Run `java -jar ./server/target/musala-api-0.0.1-SNAPSHOT.jar` for a dev server. Navigate
to `http://localhost:7444/swagger-ui/index.html` to view API specification.

### NOTE: All commands must be run from CMD from within the root repository directory

# For TESTING

### Use [UI project](https://github.com/MuhammedSabry/musala-client)

### OR after running the local server, navigate to (http://localhost:7444/swagger-ui/index.html) for manual testing
