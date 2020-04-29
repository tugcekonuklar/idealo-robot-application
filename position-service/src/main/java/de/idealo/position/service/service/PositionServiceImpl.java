package de.idealo.position.service.service;

import de.idealo.position.service.domain.FinalPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PositionServiceImpl implements PositionService {

  @Override
  public FinalPosition renderPosition(final PositionCommand command) {
    log.info("Rendering position with command: {}", command);
    return FinalPosition.builder().build();
  }

}
