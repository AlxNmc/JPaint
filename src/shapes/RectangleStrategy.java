package shapes;

import java.awt.*;

public class RectangleStrategy implements IShapeStrategy {

    public RectangleStrategy(){ }

    @Override
    public void fill(Graphics2D graphics2D, Color color, int X, int Y, int width, int height) {
        graphics2D.setColor(color);
        graphics2D.fillRect(X, Y, width, height);
    }

    @Override
    public void outline(Graphics2D graphics2D, Color color, int X, int Y, int width, int height) {
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRect(X, Y, width, height);
    }

    @Override
    public void select(Graphics2D graphics2D, int X, int Y, int width, int height) {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(X-4, Y-4, width+8, height+8);
    }
}