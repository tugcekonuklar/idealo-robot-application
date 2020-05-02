package de.idealo.position.service.api.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class PositionRequest {
  @NotNull
  private String script;
  private int x;
  private int y;
}
