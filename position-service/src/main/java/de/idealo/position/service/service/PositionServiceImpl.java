package de.idealo.position.service.service;

import de.idealo.position.service.domain.ActionPosition;
import de.idealo.position.service.domain.Directions;
import de.idealo.position.service.domain.ErrorCode;
import de.idealo.position.service.domain.FinalPosition;
import de.idealo.position.service.domain.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.idealo.position.service.domain.Directions.EAST;
import static de.idealo.position.service.domain.Directions.NORTH;
import static de.idealo.position.service.domain.Directions.SOUTH;
import static de.idealo.position.service.domain.Directions.WEST;

@Slf4j
@Service
public class PositionServiceImpl implements PositionService {
  private static final String SPLIT_NEW_LINE = "[\\r\\n]+";
  private static final String SPLIT_WHITE_SPACE = "\\s+";
  private static final String POSITION_ACTION = "POSITION";
  private static final String FORWARD_ACTION = "FORWARD";
  private static final String TURNAROUND_ACTION = "TURNAROUND";
  private static final String RIGHT_ACTION = "RIGHT";
  private static final String LEFT_ACTION = "LEFT";
  private static final String WAIT_ACTION = "WAIT";
  private static final int TURN_RIGHT_ANGLE = 270;
  private static final int TURN_LEFT_ANGLE = 90;
  private static final int TURN_AROUND_ANGLE = 180;

  @Override
  public FinalPosition renderPosition(final PositionCommand command) {
    log.info("Rendering position with command: {}", command);
    return renderSteps(getSteps(command.getScript()), command.getX(), command.getY());
  }

  private FinalPosition renderSteps(final List<String> steps,
                                    final int verticalLimit,
                                    final int horizontalLimit) {
    final var actionPositions = new ArrayList<ActionPosition>();
    var currentPosition = Position.builder().x(0).y(0).direction(EAST).build();
    for (final String step : steps) {
      final var actionPosition = renderStep(step, currentPosition, verticalLimit, horizontalLimit);
      actionPositions.add(actionPosition);
      currentPosition = actionPosition.getPosition();
    }
    return FinalPosition.builder()
      .finalPosition(currentPosition)
      .actions(actionPositions)
      .build();
  }

  private List<String> getSteps(final String script) {
    return Arrays.asList(script.split(SPLIT_NEW_LINE));
  }

  private List<String> getStepDetail(final String step) {
    return Arrays.asList(step.split(SPLIT_WHITE_SPACE));
  }

  private ActionPosition renderStep(final String step,
                                    final Position position,
                                    final int verticalLimit,
                                    final int horizontalLimit) {
    final var details = getStepDetail(step);
    final var actionPosition = ActionPosition.builder()
      .position(position)
      .valid(true)
      .build();
    switch (details.get(0).trim().toUpperCase()) {
      case POSITION_ACTION:
        return actionPosition
          .withPosition(initialisePosition(details, verticalLimit, horizontalLimit))
          .withAction(step);
      case FORWARD_ACTION:
        return moveForward(details.get(1), position, verticalLimit, horizontalLimit)
          .withAction(step);
      case RIGHT_ACTION:
        return actionPosition
          .withPosition(turnRight(position))
          .withAction(step);
      case LEFT_ACTION:
        return actionPosition
          .withPosition(turnLeft(position))
          .withAction(step);
      case TURNAROUND_ACTION:
        return actionPosition
          .withPosition(turnAround(position))
          .withAction(step);
      case WAIT_ACTION:
        return actionPosition
          .withPosition(position)
          .withAction(step);
      default:
        return actionPosition
          .withValid(false)
          .withAction(step);
    }
  }

  private Position initialisePosition(final List<String> details,
                                      final int verticalLimit,
                                      final int horizontalLimit) {
    final var vertical = Integer.parseInt(details.get(1));
    final var horizontal = Integer.parseInt(details.get(2));
    if (vertical > verticalLimit
      || vertical < 0
      || horizontal > horizontalLimit
      || horizontal < 0) {
      throw ErrorCode.INVALID_INITIAL_POSITION.asErrorResult(vertical, horizontal);
    }
    return Position.builder()
      .x(vertical)
      .y(horizontal)
      .direction(Directions.valueOf(details.get(3).toUpperCase()))
      .build();
  }

  private ActionPosition moveForward(final String stepValue,
                                     final Position position,
                                     final int verticalLimit,
                                     final int horizontalLimit) {
    final int positionX = position.getX();
    final int positionY = position.getY();
    final int step = Integer.parseInt(stepValue);
    final var actionPosition = ActionPosition.builder()
      .position(position)
      .valid(true)
      .build();
    switch (position.getDirection()) {
      case EAST:
        if (positionX + step > verticalLimit) {
          return actionPosition.withValid(false);
        }
        return actionPosition.withPosition(position.withX(positionX + step));
      case WEST:
        if (positionX - step < 0) {
          return actionPosition.withValid(false);
        }
        return actionPosition.withPosition(position.withX(positionX - step));
      case NORTH:
        if (positionY - step < 0) {
          return actionPosition.withValid(false);
        }
        return actionPosition.withPosition(position.withY(positionY - step));
      case SOUTH:
        if (positionY + step > horizontalLimit) {
          return actionPosition.withValid(false);
        }
        return actionPosition.withPosition(position.withY(positionY + step));
      default:
        return actionPosition.withValid(false);
    }
  }

  private Position turnRight(final Position position) {
    return turnPosition(position, TURN_RIGHT_ANGLE);
  }

  private Position turnLeft(final Position position) {
    return turnPosition(position, TURN_LEFT_ANGLE);
  }

  private Position turnAround(final Position position) {
    return turnPosition(position, TURN_AROUND_ANGLE);
  }

  private Position turnPosition(final Position position, final int turningAngle) {
    final int nextAngle = (position.getDirection().getAngle() + turningAngle) % 360;
    switch (Directions.findByAngle(nextAngle)) {
      case EAST:
        return position.withDirection(EAST);
      case WEST:
        return position.withDirection(WEST);
      case NORTH:
        return position.withDirection(NORTH);
      case SOUTH:
        return position.withDirection(SOUTH);
      default:
        return position;
    }
  }
}
