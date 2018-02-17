# subscription
Solution for newscorp question
1. Make sure you have java 8 installed(jdk and jre).
2. Make sure you have maven 3+ installed.
3. From the root folder /subscriptions run `mvn clean install` to compile the code.
4. Make sure nothing is running on port 8080.
5. From the root folder /subscriptions run `mvn clean spring-boot:run` to start the app.
6. Verify that the spring boot server is successfully started.

To test hit the endpoint 
http://localhost:8080/subscribe?orderDetails=${some string}

Note: Change the max log value in application.properties and restart the server to test for smaller log length.
