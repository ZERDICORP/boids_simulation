package just.curiosity.boids_simulation.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import just.curiosity.boids_simulation.core.agent.Agent;
import just.curiosity.boids_simulation.core.constants.Const;
import just.curiosity.boids_simulation.core.obstacle.Obstacle;
import just.curiosity.boids_simulation.core.vector.Vector;

public class Flock {
  private final int width;
  private final int height;
  private final List<Agent> agents;
  private final List<Obstacle> obstacles;
  private Consumer<Agent> onAgentUpdate;

  {
    agents = new ArrayList<>();
    obstacles = new ArrayList<>();
  }

  public Flock(int width, int height, int density) {
    this.width = width;
    this.height = height;

    for (int y = 0; y < height; y += height / density) {
      for (int x = 0; x < width; x += width / density) {
        agents.add(new Agent(x, y));
      }
    }
  }

  public void onAgentUpdate(Consumer<Agent> onAgentUpdate) {
    this.onAgentUpdate = onAgentUpdate;
  }

  public void addObstacle(Obstacle obstacle) {
    obstacles.add(obstacle);
  }

  public void update() {
    agents.parallelStream()
      .forEach(this::process);
  }

  private void process(Agent agent) {
    final List<Agent> agentsNearby = agents.parallelStream()
      .filter(a -> !agent.equals(a) && dist(a.location(), agent.location()) < Const.AGENT_VIEW_RADIUS)
      .toList();

    final Vector cohesionResult = cohesion(agent, agentsNearby);
    final Vector separationResult = separation(agent, agentsNearby);
    final Vector alignmentResult = alignment(agent, agentsNearby);
    final Vector avoidanceResult = avoidance(agent, obstacles);

    agent.applyForce(cohesionResult);
    agent.applyForce(separationResult);
    agent.applyForce(alignmentResult);
    agent.applyForce(avoidanceResult);

    agent.updateLocation(width, height);

    onAgentUpdate.accept(agent);
  }

  // Rule #1 ~ Finding the center of mass and push the agent in its direction.
  private Vector cohesion(Agent agent, List<Agent> otherAgents) {
    final Vector resultVector = new Vector(0, 0);
    if (otherAgents.size() == 0) {
      return resultVector;
    }

    otherAgents.forEach(a -> resultVector.add(a.location()));

    resultVector.divide(otherAgents.size());
    resultVector.subtract(agent.location());
    resultVector.multiply(Const.COHESION_FACTOR);

    return resultVector;
  }

  // Rule #2 ~ Detect agents that are too close and move the current agent in
  // the opposite direction.
  private Vector separation(Agent agent, List<Agent> otherAgents) {
    final Vector resultVector = new Vector(0, 0);
    if (otherAgents.size() == 0) {
      return resultVector;
    }

    otherAgents.forEach(a -> {
      final double dist = dist(a.location(), agent.location());
      if (dist > Const.DISTANCE_BETWEEN_AGENTS) {
        return;
      }

      final Vector direction = a.location().copy();
      direction.subtract(agent.location());
      direction.normalize();
      direction.multiply(dist);

      resultVector.subtract(direction);
    });

    resultVector.multiply(Const.SEPARATION_FACTOR);

    return resultVector;
  }

  // Rule #3 ~ Determination of the average speed of agents.
  private Vector alignment(Agent agent, List<Agent> otherAgents) {
    final Vector resultVector = new Vector(0, 0);
    if (otherAgents.size() == 0) {
      return resultVector;
    }

    otherAgents.forEach(a -> resultVector.add(a.velocity()));

    resultVector.divide(otherAgents.size());
    resultVector.subtract(agent.velocity());
    resultVector.multiply(Const.ALIGNMENT_FACTOR);

    return resultVector;
  }

  // Rule #4 ~ Avoid user's mouse position.
  private Vector avoidance(Agent agent, List<Obstacle> obstacles) {
    final Vector resultVector = new Vector(0, 0);
    if (obstacles.size() == 0) {
      return resultVector;
    }

    obstacles.forEach(obstacle -> {
      final double dist = dist(obstacle.location(), agent.location());
      if (dist < obstacle.radius()) {
        final Vector direction = obstacle.location().copy();
        direction.subtract(agent.location());
        direction.normalize();
        direction.multiply(dist);

        resultVector.subtract(direction);
      }
    });

    resultVector.multiply(Const.AVOIDANCE_FACTOR);
    return resultVector;
  }

  private double dist(Vector v1, Vector v2) {
    final double diffX = v2.x() - v1.x();
    final double diffY = v2.y() - v1.y();
    return Math.sqrt((diffX * diffX) + (diffY * diffY));
  }
}
