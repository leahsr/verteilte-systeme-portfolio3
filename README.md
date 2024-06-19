## How to start the partner university management application

### Use class `Start`

Execute method `main` in class `Start`. This will start the embedded Tomcat server and deploy the application. The application
is available at `http://localhost:8080/partneruniversitymanagement/api`.

### Use Docker

### For integration testing

Call `mvn verify` to start the integration tests. This will create a Docker image and start a container for the application.  
Then the integration tests will be executed. Finally, the container will be stopped and removed. All integration are located in the `TestPartnerUniversityManagerAppIT` class.

