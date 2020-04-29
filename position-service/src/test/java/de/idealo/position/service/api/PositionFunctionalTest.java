package de.idealo.position.service.api;

import de.idealo.position.service.api.dto.ErrorResponse;
import de.idealo.position.service.api.dto.PositionResponse;
import de.idealo.position.service.domain.Directions;
import de.idealo.position.service.domain.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

class PositionFunctionalTest extends CommonFunctionalTest {

  @Test
  @DisplayName("Render Position works successfully")
  public void shouldRenderPositionWorksSuccessfully() {
    final var result = postRenderPositionSuccessfully();

    assertEquals(4, result.getFinalPosition().getX());
    assertEquals(3, result.getFinalPosition().getY());
    assertEquals(Directions.EAST, result.getFinalPosition().getDirection());
    assertTrue(result.getActions().size() == 2);
    assertEquals(Directions.EAST, result.getActions().get(0).getPosition().getDirection());
    assertEquals(1, result.getActions().get(0).getPosition().getX());
    assertEquals(3, result.getActions().get(0).getPosition().getY());
    assertEquals(Directions.EAST, result.getActions().get(1).getPosition().getDirection());
    assertEquals(4, result.getActions().get(1).getPosition().getX());
    assertEquals(3, result.getActions().get(1).getPosition().getY());
  }

  @Test
  @DisplayName("Render Position returns bad request cause of invalid initial position")
  public void shouldRenderPositionReturnsBadRequest() {
    final var result = postRenderPositionReturnsBadRequest();

    assertEquals(BAD_REQUEST.value(), result.getStatus());
    assertEquals(ErrorCode.INVALID_INITIAL_POSITION.name(), result.getCode());
    assertEquals(ErrorCode.INVALID_INITIAL_POSITION.getStatus().name(), result.getType());
  }

  @Test
  @DisplayName("Render Position returns bad request cause of null script")
  public void shouldRenderPositionReturnsBadRequestForNullInput() {
    final var result = postRenderPositionReturnsBadRequestNullInput();

    assertEquals(BAD_REQUEST.value(), result.getStatus());
    assertEquals(ErrorCode.INVALID_REQUEST.name(), result.getCode());
    assertEquals(ErrorCode.INVALID_REQUEST.getStatus().name(), result.getType());
  }


  private PositionResponse postRenderPositionSuccessfully() {
    return requestSpecification()
      .body("{\n"
        + "   \"script\":\"POSITION 1 3 EAST\\nFORWARD 3\",\n"
        + "   \"x\":5,\n"
        + "   \"y\":5\n"
        + "}")
      .post("/api/positions")
      .then()
      .log()
      .all()
      .statusCode(OK.value())
      .extract()
      .as(PositionResponse.class);
  }

  private ErrorResponse postRenderPositionReturnsBadRequest() {
    return requestSpecification()
      .body("{\n"
        + "   \"script\":\"POSITION 1 6 EAST\\nFORWARD 3\",\n"
        + "   \"x\":5,\n"
        + "   \"y\":5\n"
        + "}")
      .post("/api/positions")
      .then()
      .log()
      .all()
      .statusCode(BAD_REQUEST.value())
      .extract()
      .as(ErrorResponse.class);
  }

  private ErrorResponse postRenderPositionReturnsBadRequestNullInput() {
    return requestSpecification()
      .body("{\n"
        + "   \"script\": null ,\n"
        + "   \"x\":5,\n"
        + "   \"y\":5\n"
        + "}")
      .post("/api/positions")
      .then()
      .log()
      .all()
      .statusCode(BAD_REQUEST.value())
      .extract()
      .as(ErrorResponse.class);
  }

}
