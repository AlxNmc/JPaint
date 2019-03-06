package main;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.*;

public interface IShape {
    void setColors(Color primary, Color secondary);
    void setShadingType(ShapeShadingType shadingType);
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void draw(Graphics2D graphics2D);
    void drawOutline(Graphics2D graphics2D);
    void move(int xOffset, int yOffset);
    IShape clone();
}
