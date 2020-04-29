package de.idealo.position.service.api.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class PositionRequest {
  @NonNull
  private String script;
  private int x;
  private int y;
}
