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
}
