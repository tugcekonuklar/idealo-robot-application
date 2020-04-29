package de.idealo.position.service.domain;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class FinalPosition {
  private Position finalPosition;
  private List<ActionPosition> actions;
}
