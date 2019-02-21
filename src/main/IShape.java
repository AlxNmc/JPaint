package main;

import java.awt.*;

public interface IShape {
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void draw(Graphics2D graphics2D);
    void move(int newX, int newY);
}
