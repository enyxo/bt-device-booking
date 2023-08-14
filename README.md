# BT Device Booking

### Tech, frameworks

* Java version : 17
* Gradle version : 8.2.1
* Spring boot version : 3.1.2

### Application configurations and properties.

**DB:** In memory [H2-database](https://www.h2database.com/html/main.html) with [Flyway](https://flywaydb.org/)
migration support.
Flyway migration script can be found under resources/db/migration

### Installation

#### Running application in local machine

```shell
cd bt-device-booking
./gradlew clean build
cd cd build/libs
java -jar bt-device-booking-0.0.1.jar
```

#### Running application in docker environment

_Note: docker should be installed in running machine :)_

```shell
cd bt-device-booking
docker build -t bt-device-booking .
docker run --name bt-device-booking -dp 8080:8080 bt-device-booking
docker start bt-device-booking
```

#### Usage, api

**Application contains swagger configuration**\
link to swagger api - http://localhost:8080/api/v1/bt/swagger-ui/index.html

Also, You can find postman collection examples under postman-collection directory

### REFLECTION:

**What aspect of this exercise did you find most interesting?**\
_Interesting path of application is that to handle remote calls with transactional context_

**What did you find most cumbersome?**\
_Nothing :)_

### Remote mobile spec service

**Unfortunately Fonoapi not working**\
**Using RapidApi https://rapidapi.com/**
