package de.idealo.position.service.api;

import de.idealo.position.service.api.dto.PositionRequest;
import de.idealo.position.service.api.dto.PositionResponse;
import de.idealo.position.service.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static de.idealo.position.service.api.PositionConverter.toCommand;
import static de.idealo.position.service.api.PositionConverter.toResponse;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/positions")
public class PositionController {

  private final PositionService service;

  @PostMapping
  @ResponseStatus(OK)
  public PositionResponse render(@RequestBody @Valid final PositionRequest request) {
    log.info("Render a position with rotation request : {}", request);
    return toResponse(service.renderPosition(toCommand(request)));
  }

}
