package de.idealo.position.service.service;

public class PositionRenderFixture {
  public static PositionCommand someCommand() {
    return PositionCommand.builder()
      .script("POSITION 1 3 EAST\n"
        + "FORWARD 3")
      .x(5)
      .y(5)
      .build();
  }
}

