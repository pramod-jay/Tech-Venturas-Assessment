# Tech-Venturas-Assessment
## TV GATEWAY MANAGEMENT PORTAL

### Overview
The TV Gateway Management Portal provides a RESTful API suite for managing gateways and their associated peripheral devices. It enables users to perform CRUD operations on gateways and devices, ensuring validation of fields and adherence to predefined constraints.

### Build With
The project is implemented using the following frameworks and Databases
* ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)

## Getting Started

### Prerequisites
* ![JDK 17](https://img.shields.io/badge/JDK-17-007396?style=flat&logo=java&logoColor=white)
* ![Maven 3.9](https://img.shields.io/badge/Maven-3.9-C71A36?style=flat&logo=apache-maven&logoColor=white)
* ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=flat&logo=postman&logoColor=white)

## Implementation
Steps to implement and set up the project:
1. Clone the repo
    ```sh
   git clone https://github.com/pramod-jay/Tech-Venturas-Assessment.git
   ```
    
2. Database Implementation
  <br/>The MySQL database server is hosted on Azure flexible server, eliminating the need for database server implementation locally. Details regarding the database configuration can be found in the <b>‘src/main/resources/application.properties’</b> file.

3. Open the project with a preferred IDE such as IntelliJ IDEA, VSCode.
   <br/>(Completion of successful opening will result in the project with the following folders and files)
   <img width="321" alt="Screenshot 2024-02-21 at 23 49 08" src="https://github.com/pramod-jay/Tech-Venturas-Assessment/assets/91390000/c60d9249-8701-4b06-9634-d89aeb6fb621">

4. Through the following path <b>‘src/main/java/com/techventuras/TechVenturas/TechVenturasApplication.java’</b>, Run the <b>TechVenturasApplication.java</b> application.

5. Import <b>TechVenturas.postman_collection.json</b> to the Postman which is in the root folder.
   <img width="443" alt="Screenshot 2024-02-21 at 22 49 41" src="https://github.com/pramod-jay/Tech-Venturas-Assessment/assets/91390000/0ef4a546-c1cf-4a1e-85f2-1c67cda51967">
   
6. Now the environment is ready for testing via Postman.👏<br/>
   ![Screenshot 2024-02-21 at 22 50 59](https://github.com/pramod-jay/Tech-Venturas-Assessment/assets/91390000/7efe202c-e744-401e-b6ea-b909d1da805a)

## Assumptions
* A gateway requires a name and an IP address.
* Peripheral devices are not mandatory for a gateway; they can exist independently.
* Peripheral devices can also exist without being associated with a gateway.
* If a gateway is deleted, the connected peripheral devices may not be deleted; instead, the foreign keys indicating the devices' connection to the gateway will become null.
