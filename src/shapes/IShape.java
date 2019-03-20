package shapes;

import java.awt.*;
import java.util.ArrayDeque;

public interface IShape {
    void draw(Graphics2D graphics2D);
    void drawSelected(Graphics2D graphics2D);
    void move(int xOffset, int yOffset);
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    ArrayDeque<IShape> getMembers();
    IShape clone();
}
