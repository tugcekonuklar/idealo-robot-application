package de.idealo.position.service.service;

import de.idealo.position.service.domain.FinalPosition;

public interface PositionService {
  FinalPosition renderPosition(final PositionCommand command);
}
