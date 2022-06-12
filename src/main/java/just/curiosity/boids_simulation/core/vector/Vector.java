package just.curiosity.boids_simulation.core.vector;

public class Vector {
  private double x;
  private double y;

  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double x() {
    return x;
  }

  public double y() {
    return y;
  }

  public void add(Vector v) {
    x += v.x;
    y += v.y;
  }

  public void divide(double n) {
    x /= n;
    y /= n;
  }

  public void multiply(double n) {
    x *= n;
    y *= n;
  }

  public void subtract(Vector v) {
    x -= v.x;
    y -= v.y;
  }

  public void normalize() {
    double magnitude = magnitude();
    if (magnitude > 0) {
      x /= magnitude;
      y /= magnitude;
    }
  }

  public double magnitude() {
    return Math.sqrt(x * x + y * y);
  }

  public Vector copy() {
    return new Vector(x, y);
  }

  public void limit(double limit) {
    final double n = magnitude();
    if (n > limit) {
      divide(n);
      multiply(limit);
    }
  }

  public double theta() {
    return Math.atan2(-y, -x);
  }

  public void multiplyX(int n) {
    x *= n;
  }

  public void multiplyY(int n) {
    y *= n;
  }

  public void update(double x, double y) {
    this.x = x;
    this.y = y;
  }
}
