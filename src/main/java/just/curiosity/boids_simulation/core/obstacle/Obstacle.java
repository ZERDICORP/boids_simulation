package just.curiosity.boids_simulation.core.obstacle;

import just.curiosity.boids_simulation.core.vector.Vector;

public class Obstacle {
  private final double radius;
  private final Vector location;

  public Obstacle(double radius) {
    this.radius = radius;
    location = new Vector(-radius, -radius);
  }

  public double radius() {
    return radius;
  }

  public Vector location() {
    return location;
  }

  public void updateLocation(double x, double y) {
    location.update(x, y);
  }
}
