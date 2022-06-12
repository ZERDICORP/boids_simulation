package just.curiosity.boids_simulation.gui.interaction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import just.curiosity.boids_simulation.core.constants.Const;

public final class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
  private static int currentX;
  private static int currentY;

  public int getX() {
    return currentX;
  }

  public int getY() {
    return currentY;
  }

  @Override
  public void mouseDragged(MouseEvent mouseEvent) {
    currentX = mouseEvent.getX();
    currentY = mouseEvent.getY();
  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {
    currentX = (int) -Const.MOUSE_OBSTACLE_RADIUS;
    currentY = (int) -Const.MOUSE_OBSTACLE_RADIUS;
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {
  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {
  }

  @Override
  public void mouseEntered(MouseEvent mouseEvent) {
  }

  @Override
  public void mouseExited(MouseEvent mouseEvent) {
  }
}
