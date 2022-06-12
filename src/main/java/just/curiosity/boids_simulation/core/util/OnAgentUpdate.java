package just.curiosity.boids_simulation.core.util;

import just.curiosity.boids_simulation.core.agent.Agent;

public interface OnAgentUpdate {
  void apply(Agent agent);
}
