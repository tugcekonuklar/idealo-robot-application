package de.idealo.position.service.service;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotNull;

@Value
@Builder
@With
public class PositionCommand {
  @NotNull
  private String script;
  private int x;
  private int y;
}
