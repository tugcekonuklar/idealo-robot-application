package de.idealo.position.service.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.is;

class ActuatorFunctionalTest extends CommonFunctionalTest {

  @Test
  void thatHealthEndpointContainsCurrentServerStatus() {
    requestSpecification()
      .when()
      .get("/internal/health")
      .then()
      .log().all()
      .and()
      .assertThat()
      .statusCode(HttpStatus.OK.value())
      .body("status", is("UP"));
  }
}
