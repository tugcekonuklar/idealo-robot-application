package de.idealo.position.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Directions {
  EAST(0),
  WEST(180),
  NORTH(90),
  SOUTH(270);

  private int angle;

  public static Directions findByAngle(final int angle) {
    switch (angle) {
      case 0:
        return EAST;
      case 90:
        return NORTH;
      case 180:
        return WEST;
      case 270:
        return SOUTH;
      default:
        throw ErrorCode.INVALID_ANGLE.asErrorResult(angle);
    }
  }
}
