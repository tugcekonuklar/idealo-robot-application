package de.idealo.position.service.service;

import de.idealo.position.service.domain.ErrorCode;
import de.idealo.position.service.domain.ErrorResultException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static de.idealo.position.service.domain.FinalPositionFixture.someFinalPosition;
import static de.idealo.position.service.service.PositionRenderFixture.someCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PositionServiceTest {

  @Spy
  @InjectMocks
  private PositionServiceImpl service;

  @Test
  @DisplayName("Render Position works successfully")
  public void shouldRenderSuccessfully() {
    final var command = someCommand();
    final var expectedResult = someFinalPosition();

    final var result = service.renderPosition(command);

    assertEquals(result, expectedResult);
  }

  @Test
  @DisplayName("Render Position throws invalid initial position")
  public void shouldRenderThrowsInvalidInitial() {
    final var invalidCommand = someCommand().withX(1).withY(1);

    final var result = assertThrows(ErrorResultException.class, () -> service.renderPosition(invalidCommand));
    assertNotNull(result.getMessage());
    assertEquals(result.getCode(), ErrorCode.INVALID_INITIAL_POSITION.name());
    assertEquals(result.getType(), ErrorCode.INVALID_INITIAL_POSITION.getStatus().name());
  }

}
