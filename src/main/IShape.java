package main;

import model.ShapeType;

import java.awt.*;

public interface IShape {
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    ShapeType getShapeType();
    void draw(Graphics2D graphics2D);
    void move(int newX, int newY, Graphics2D graphics2D);
}
