package de.idealo.position.service.service;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class PositionCommand {
  private String script;
  private int x;
  private int y;
}
