# Position Render Service

This project provides endpoint and data to render final position and
the list of action details with result position of each step of actions and validity according to input command.


To be able to render position specific input parameters are necessary:
* Script (script command to give some movement)
* x (max limit of vertical layer of grid)
* y (max limit of horizontal layer of grid)

This service provides you as a some information are below:

* List of actions
    * action command which is running
    * result position of the action
    * validity of the action. When action runs successfully  validation sets true, otherwise is false.
* Final Position of all actions with direction

** If the action is invalid or exceeds the max limit of the grid, position does not change end of the action step and validation sets false.
   With these result of actions client can investigate each step and understand of the result of the actions.

## Sample

#### Request:

```json
{
   "script":"POSITION 1 3 EAST\nFORWARD 3",
   "x":5,
   "y":5
}
```

#### Response:

```json
{
    "actions": [
        {
            "action": "POSITION 1 3 EAST",
            "position": {
                "x": 1,
                "y": 3,
                "direction": "EAST"
            },
            "valid": true
        },
        {
            "action": "FORWARD 3",
            "position": {
                "x": 4,
                "y": 3,
                "direction": "EAST"
            },
            "valid": true
        }
    ],
    "final": {
        "x": 4,
        "y": 3,
        "direction": "EAST"
    }
}
```


## Assumptions

* Don't see any restriction for using Lombok, that's why I used Lombok.

## API Swagger Documentation

To have more detail about service you can use Position Render Service REST API documentation,
You can see from `http://localhost:8080/swagger-ui.html` address.

## Libraries & Tools

* Java 11
* Maven 3.6
* Spring Boot 2
* Lombok
* Junit 5
* Mockito
* RestAssured
* Checkstyle
* SwaggerUI
* CircleCI
* Docker

## Building Application

Always use the Maven wrapper (`mvn build`) to build the project from command line.

App runs default `8080` port. `http://localhost:8080/`

Useful commands:

* `mvn clean install` -- fully clean and install dependencies
* `mvn test` -- runs tests
* `mvn compile` -- compile application
* `mvn package` -- create package of application
* `mvn clear verify` -- runs CheckStyle

## Running Application

After you create package of the application, in the path of the application on terminal, write the command which is below to run application.

`javac -jar target/position-service-0.0.1-SNAPSHOT.jar`

Run with command:

`mvn spring-boot:run` - starts application


## Importing & Building


### 1. Enable Plugins

Enable the following plugins in IDEA:
* [Lombok Plugin](https://projectlombok.org/setup/eclipse)

### 2. Configure Annotation Processing

##### For Intellij IDEA:

Open _Settings_ → _Build, Execution, Deployment_ → _Compiler_ →
_Annotation Processors_.

Check options:
* _Enable annotation processing_

###### Don't forget to enable Lombok in your IDEA

For Intelij IDEA, you can follow below

Open _Settings_ → _Other settings_ → _Lombok plugin_.

Check _Enable Lombok plugin for this project_.

## Health Check

You can see from `http://localhost:8080/internal/health` address

 ```json
{
   "status":"UP",
   "components":{
      "diskSpace":{
         "status":"UP",
         "details":{
            "total":121123069952,
            "free":26156806144,
            "threshold":10485760
         }
      },
      "ping":{
         "status":"UP"
      }
   }
}
```

## Needs To Improve

* Improve code more functional way .

