package shapes;

import java.awt.*;

public interface IShapeStrategy {
    void fill(Graphics2D graphics2D, Color color, int X, int Y, int width, int height);
    void outline(Graphics2D graphics2D, Color color,int X, int Y, int width, int height);
    void select(Graphics2D graphics2D, int X, int Y, int width, int height);
}
