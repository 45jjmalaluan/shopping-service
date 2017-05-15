Shopping Service
================

### Database
* Installation of PostgreSQL and creation of a local DB is required.
* Make sure postgres is started.

### Setting the Properties
Supply the necessary values.
```
# E.g. db.url=jdbc:postgresql://localhost:5432/local_db
db.url=<value>
db.username=<value>
db.password=<value>
```

### Running with Spring Boot

See reference for executing with gradle on a different OS.
https://docs.gradle.org/current/userguide/gradle_wrapper.html

The following command will run the app.
`./gradlew clean bootRun`

* Check the local database if the tables and data have been created after the app have started.

### Running tests

The following command will build the app and run the necessary unit tests.
`./gradlew clean build`

* The tests depends on the tables that were created from running the app.

### Creating the war file

The following command will build the war file.
`./gradlew clean war`