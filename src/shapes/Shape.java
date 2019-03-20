package shapes;

import model.ShapeShadingType;

import java.awt.*;
import java.util.ArrayDeque;

public class Shape implements IShape {

    private IShapeStrategy shapeStrategy;
    private Color primary;
    private Color secondary;
    private ShapeShadingType shadingType;
    private int X;
    private int Y;
    private int width;
    private int height;

    public Shape(IShapeStrategy shapeStrategy, Color primary, Color secondary, ShapeShadingType shadingType, int X, int Y, int width, int height){
        this.shapeStrategy = shapeStrategy;
        this.primary = primary;
        this.secondary = secondary;
        this.shadingType = shadingType;
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;
    }

    public Shape(int X, int Y, int width, int height){
        shapeStrategy = new RectangleStrategy();
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D graphics2D){
        switch (shadingType){
            case OUTLINE:
                shapeStrategy.outline(graphics2D, primary, X, Y, width, height);
                break;
            case FILLED_IN:
                shapeStrategy.fill(graphics2D, primary, X, Y, width, height);
                shapeStrategy.outline(graphics2D, primary, X, Y, width, height);
                break;
            case OUTLINE_AND_FILLED_IN:
                shapeStrategy.fill(graphics2D, primary, X, Y, width, height);
                shapeStrategy.outline(graphics2D, secondary, X, Y, width, height);
        }
    }

    public void drawSelected(Graphics2D graphics2D){
        draw(graphics2D);
        shapeStrategy.select(graphics2D, X, Y, width, height);
    }

    public void move(int xOffset, int yOffset){
        X = X+xOffset;
        Y = Y+yOffset;
    }

    public int getX() { return X; }

    public int getY() { return Y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public ArrayDeque<IShape> getMembers() {
        ArrayDeque<IShape> l = new ArrayDeque<>();
        l.add(this);
        return l;
    }

    public IShape clone() {
        return new Shape(this.shapeStrategy, this.primary, this.secondary, this.shadingType, this.X, this.Y, this.width, this.height);
    }
}
