package de.idealo.position.service.api.error;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import de.idealo.position.service.api.dto.ErrorResponse;
import de.idealo.position.service.domain.ErrorCode;
import de.idealo.position.service.domain.ErrorResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorResponseControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolationException(final ConstraintViolationException ex) {
    return ResponseEntity.status(BAD_REQUEST)
      .body(ErrorResponse.builder()
        .code(ErrorCode.INVALID_REQUEST.name())
        .type(BAD_REQUEST.name())
        .message(ex.getMessage())
        .status(ErrorCode.INVALID_REQUEST.getStatus().value())
        .build()
      );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
    return ResponseEntity.status(BAD_REQUEST)
      .body(ErrorResponse.builder()
        .code(ErrorCode.INVALID_REQUEST.name())
        .type(BAD_REQUEST.name())
        .message(ex.getMessage())
        .status(ErrorCode.INVALID_REQUEST.getStatus().value())
        .build()
      );
  }

  @ExceptionHandler(ErrorResultException.class)
  public ResponseEntity<ErrorResponse> handleErrorResultException(final ErrorResultException ex) {
    return ResponseEntity.status(ex.getStatus())
      .body(ErrorResponse.builder()
        .code(ex.getCode())
        .type(ex.getType())
        .message(ex.getMessage())
        .status(ex.getStatus())
        .build()
      );
  }

  @ExceptionHandler(JsonParseException.class)
  public ResponseEntity<ErrorResponse> handleJsonParseExceptionException(final JsonParseException ex) {
    return ResponseEntity.status(BAD_REQUEST)
      .body(ErrorResponse.builder()
        .code(ErrorCode.INVALID_REQUEST.name())
        .type(BAD_REQUEST.name())
        .message(ex.getMessage())
        .status(ErrorCode.INVALID_REQUEST.getStatus().value())
        .build()
      );
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException ex) {
    return ResponseEntity.status(BAD_REQUEST)
      .body(ErrorResponse.builder()
        .code(ErrorCode.INVALID_REQUEST.name())
        .type(BAD_REQUEST.name())
        .message(ex.getMessage())
        .status(ErrorCode.INVALID_REQUEST.getStatus().value())
        .build()
      );
  }

  @ExceptionHandler(ValueInstantiationException.class)
  public ResponseEntity<ErrorResponse> handleIValueInstantiationException(final ValueInstantiationException ex) {
    return ResponseEntity.status(BAD_REQUEST)
      .body(ErrorResponse.builder()
        .code(ErrorCode.INVALID_REQUEST.name())
        .type(BAD_REQUEST.name())
        .message(ex.getMessage())
        .status(ErrorCode.INVALID_REQUEST.getStatus().value())
        .build()
      );
  }
}
