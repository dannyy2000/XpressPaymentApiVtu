# XpressAirtimeVtuApi

This is a java based API service . It provides functionalitiy for user authentication using JSON Web Token (JWT) and facilitates airtime payments using the VTU API.

         AUTHENTICATION API USING JWT
The API service includes endpoints for using authentication using JSON Web Tokens (JWT). It includes the following endpoints.

/api/v1/auth/getToken(POST): This API allows USERS getToken when they request for signUp by providing an email.
/api/v1/auth/confirmToken(POST): This API allows USERS confirm the token sent when they request for sign up.
/api/v1/auth/register(POST): This API allows USERS complete sign up by providing additional details
/api/v1/auth/authenticate(POST): Authenticate a user by providing the email and password.

         Airtime Payments with VTU API
/airtime(POST): This initiate an airtime top us for a user by providing the necessary details

           EXTERNAL TOOLS
SQL Database: This applicatiom uses sql database for database storage. 
JUnit: JUnit is a testing framework for java applications . It is used in this project to write and execute unit test to ensure the correctness of the code.
Sendinblue: This is a cloud based email provider . It is used in the project  to send sms notifications to users for account activation

             Getting Started
To set up and run this API service locally, follow these steps:

Clone the repository: git clone https://github.com/your-username/repo-name.git
Install any necessary dependencies specified in the project's build file (e.g., pom.xml for Maven or build.gradle for Gradle).
Configure the necessary environment variables, including database connection details and any API keys required by the VTU API  and Sendinblue. 
Start the application by running the main class or using the appropriate build tool command.
The API service runs  on http://localhost:8080 


             Documentation
The API endpoints and their usage are documented in the API documentation. Please refer to the documentation for detailed information on how to use the API service and interact with the endpoints.
https://documenter.getpostman.com/view/28560616/2s946fesrE#0c9700f4-908a-47b8-8f74-bedbdb3eefe9
              


