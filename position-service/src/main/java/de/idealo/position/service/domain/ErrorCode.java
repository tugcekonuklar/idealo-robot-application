package de.idealo.position.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  INVALID_REQUEST(BAD_REQUEST, "Invalid client request : {0}");

  private HttpStatus status;
  private String message;

  public ErrorResultException asErrorResult(final Object... params) {
    return ErrorResultException.builder()
      .code(name())
      .type(status.name())
      .status(status.value())
      .message(MessageFormat.format(message, params))
      .build();

  }
}
