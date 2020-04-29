package de.idealo.position.service.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.idealo.position.service.domain.ActionPosition;
import de.idealo.position.service.domain.Position;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PositionResponse {
  @JsonProperty("final")
  private Position finalPosition;
  private List<ActionPosition> actions;
}
