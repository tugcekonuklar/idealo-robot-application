package de.idealo.position.service.domain;

public class PositionFixture {
  public static Position somePosition() {
    return Position.builder()
      .x(4)
      .y(3)
      .direction(Directions.EAST)
      .build();
  }
}
