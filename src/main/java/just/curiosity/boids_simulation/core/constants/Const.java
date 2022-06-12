package just.curiosity.boids_simulation.core.constants;

import java.awt.Color;

public interface Const {
  Color AGENT_COLOR = new Color(107, 223, 255);
  double BORDER_WIDTH = 10;
  double MOUSE_OBSTACLE_RADIUS = 65;
  double DISTANCE_BETWEEN_AGENTS = 15;
  double AGENT_VIEW_RADIUS = 60;
  double AGENT_MAX_SPEED = 2.25;
  double SEPARATION_FACTOR = .03;
  double ALIGNMENT_FACTOR = .1;
  double COHESION_FACTOR = .005;
  double AVOIDANCE_FACTOR = 0.05;
  double ACCELERATION_FACTOR = .1;
  double PIXEL_FADE_FACTOR = .2;
}
