package de.idealo.position.service.domain;

import java.util.List;

import static de.idealo.position.service.domain.PositionFixture.somePosition;

public class ActionPositionFixture {

  public static ActionPosition someActionBuilder() {
    return ActionPosition.builder()
      .position(somePosition())
      .valid(true)
      .action("POSITION 1 3 EAST")
      .build();
  }

  public static List<ActionPosition> someActionPositionList() {
    return List.of(
      someActionBuilder()
        .withPosition(somePosition().withX(1).withY(3)),
      someActionBuilder()
        .withPosition(somePosition().withX(4).withY(3))
        .withAction("FORWARD 3")
    );
  }
}
