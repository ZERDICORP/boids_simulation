package just.curiosity.boids_simulation;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import just.curiosity.boids_simulation.core.Flock;
import just.curiosity.boids_simulation.core.constants.Const;
import just.curiosity.boids_simulation.core.obstacle.Obstacle;
import just.curiosity.boids_simulation.core.vector.Vector;
import just.curiosity.boids_simulation.gui.Window;
import just.curiosity.boids_simulation.gui.interaction.Keyboard;
import just.curiosity.boids_simulation.gui.interaction.Mouse;

public class Main {
  private static final int width;
  private static final int height;
  private static final Keyboard keyboard;
  private static final Mouse mouse;
  private static final int[] pixelBuffer;
  private static final Window window;
  private static final Obstacle mouseObstacle;
  private static final Flock flock;
  private static boolean isRunning;

  static {
    width = 1000;
    height = 700;
    keyboard = new Keyboard();
    mouse = new Mouse();
    isRunning = true;

    final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    pixelBuffer = ((DataBufferInt) bufferedImage.getRaster()
      .getDataBuffer())
      .getData();

    window = new Window(bufferedImage, keyboard, mouse);
    mouseObstacle = new Obstacle(Const.MOUSE_OBSTACLE_RADIUS);
    flock = new Flock(width, height, 35);
    flock.onAgentUpdate(agent -> {
      // Draw the agent's wings (something like this - "/\").
      drawLine(agent.location(), agent.velocity().theta() + Math.toRadians(30), 5, Const.AGENT_COLOR);
      drawLine(agent.location(), agent.velocity().theta() + Math.toRadians(-30), 5, Const.AGENT_COLOR);
    });
    flock.addObstacle(mouseObstacle);
  }

  private static boolean inRange(double x, double y) {
    return (x >= 0 && x < width) && (y >= 0 && y < height);
  }

  private static void drawLine(Vector v, double theta, double length, Color color) {
    for (double i = 0; i < length; i++) {
      final double nX = v.x() + Math.cos(theta) * i;
      final double nY = v.y() + Math.sin(theta) * i;

      final int iX = (int) nX;
      final int iY = (int) nY;
      if (inRange(iX, iY)) {
        pixelBuffer[iY * width + iX] = color.getRGB();
      }
    }
  }

  private static void interactionControl() {
    if (keyboard.getCurrentKeyCode() == KeyEvent.VK_ESCAPE) {
      stop();
    }

    mouseObstacle.updateLocation(mouse.getX(), mouse.getY());
  }

  private static void updateAndDraw() {
    interactionControl();

    // Gradually reduce the value of each pixel to create a tail effect.
    for (int i = 0; i < pixelBuffer.length; i++) {
      pixelBuffer[i] *= Const.PIXEL_FADE_FACTOR;
    }

    flock.update();
    window.draw();
  }

  public static void start() {
    long start = System.currentTimeMillis();
    int frames = 0;

    while (isRunning) {
      long end = System.currentTimeMillis();
      if (end - start >= 1000) {
        System.out.print("\rFPS: " + frames + "    ");
        frames = 0;
        start = end;
      }

      updateAndDraw();

      frames++;
    }

    window.dispose();
  }

  private static void stop() {
    isRunning = false;
  }

  public static void main(String[] args) {
    start();
  }
}
