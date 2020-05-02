package de.idealo.position.service.service;

import de.idealo.position.service.domain.FinalPosition;

import javax.validation.Valid;

public interface PositionService {
  FinalPosition renderPosition(@Valid final PositionCommand command);
}
