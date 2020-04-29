package de.idealo.position.service.api;

import de.idealo.position.service.api.dto.PositionRequest;
import de.idealo.position.service.api.dto.PositionResponse;
import de.idealo.position.service.domain.FinalPosition;
import de.idealo.position.service.service.PositionCommand;
import lombok.experimental.UtilityClass;

@UtilityClass
class PositionConverter {
  static PositionCommand toCommand(final PositionRequest request) {
    return PositionCommand.builder()
      .script(request.getScript())
      .x(request.getX())
      .y(request.getY())
      .build();
  }

  static PositionResponse toResponse(final FinalPosition position) {
    return PositionResponse.builder()
      .finalPosition(position.getFinalPosition())
      .actions(position.getActions())
      .build();
  }
}
