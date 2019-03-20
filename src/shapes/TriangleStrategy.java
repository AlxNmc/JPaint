package shapes;

import java.awt.*;

public class TriangleStrategy implements IShapeStrategy {

    private int[] xPoints;
    private int[] yPoints;

    public TriangleStrategy(){
        xPoints = new int[3];
        yPoints = new int[3];
    }

    private void setPoints(int X, int Y, int width, int height){
        xPoints[0] = X;
        yPoints[0] = Y + height;
        xPoints[1] = X + width/2;
        yPoints[1] = Y;
        xPoints[2] = X + width;
        yPoints[2] = Y + height;
    }

    @Override
    public void fill(Graphics2D graphics2D, Color color, int X, int Y, int width, int height) {
        setPoints(X, Y, width, height);
        graphics2D.setColor(color);
        graphics2D.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void outline(Graphics2D graphics2D, Color color, int X, int Y, int width, int height) {
        setPoints(X, Y, width, height);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void select(Graphics2D graphics2D, int X, int Y, int width, int height) {
        setPoints(X-4, Y-4, width+8, height+8);
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawPolygon(xPoints, yPoints, 3);
    }
}
