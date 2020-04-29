package de.idealo.position.service.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class ActionPosition {
  private String action;
  private Position position;
  private boolean isValid;
}
