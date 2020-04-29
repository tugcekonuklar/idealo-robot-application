package de.idealo.position.service.domain;

import static de.idealo.position.service.domain.ActionPositionFixture.someActionPositionList;
import static de.idealo.position.service.domain.PositionFixture.somePosition;

public class FinalPositionFixture {

  public static FinalPosition someFinalPosition() {
    return FinalPosition.builder()
      .finalPosition(somePosition())
      .actions(someActionPositionList())
      .build();
  }
}
