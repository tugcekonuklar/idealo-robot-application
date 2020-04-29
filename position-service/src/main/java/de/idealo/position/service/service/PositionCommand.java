package de.idealo.position.service.service;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PositionCommand {
  private String script;
  private int x;
  private int y;
}