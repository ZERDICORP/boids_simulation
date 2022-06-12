package just.curiosity.boids_simulation.core.agent;

import just.curiosity.boids_simulation.core.constants.Const;
import just.curiosity.boids_simulation.core.vector.Vector;

public class Agent {
  private final Vector location;
  private final Vector velocity;
  private final Vector acceleration;

  {
    velocity = new Vector(0, 0);
    acceleration = new Vector(0, 0);
  }

  public Agent(double x, double y) {
    location = new Vector(x, y);
  }

  public Vector location() {
    return location;
  }

  public Vector velocity() {
    return velocity;
  }

  public void applyForce(Vector force) {
    acceleration.add(force);
  }

  public void updateLocation(int width, int height) {
    acceleration.multiply(Const.ACCELERATION_FACTOR);

    velocity.add(acceleration);
    velocity.limit(Const.AGENT_MAX_SPEED);

    location.add(velocity);

    acceleration.multiply(0);

    // If you went beyond the horizontal boundaries - change direction
    // to the opposite.
    if (location.x() >= (width - Const.BORDER_WIDTH)) {
      if (velocity.x() > 0) {
        velocity.multiplyX(-1);
      }
    } else if (location.x() <= Const.BORDER_WIDTH) {
      if (velocity.x() < 0) {
        velocity.multiplyX(-1);
      }
    }

    // If you went beyond the vertical boundaries - change direction
    // to the opposite.
    if (location.y() >= (height - Const.BORDER_WIDTH)) {
      if (velocity.y() > 0) {
        velocity.multiplyY(-1);
      }
    } else if (location.y() <= Const.BORDER_WIDTH) {
      if (velocity.y() < 0) {
        velocity.multiplyY(-1);
      }
    }
  }
}
