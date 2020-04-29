package de.idealo.position.service.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class Position {
  private int x;
  private int y;
  private Directions direction;
}
