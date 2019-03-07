package main;

import model.ShapeShadingType;

import java.awt.*;

public abstract class ShapeDecorator implements IShape{
    protected IShape decoratedShape;
    public ShapeDecorator(IShape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void setColors(Color primary, Color secondary) { decoratedShape.setColors(primary, secondary); }
    public void setShadingType(ShapeShadingType shadingType) {decoratedShape.setShadingType(shadingType);}
    public int getX(){return decoratedShape.getX();}
    public int getY(){return decoratedShape.getY();}
    public int getWidth(){return decoratedShape.getWidth();}
    public int getHeight(){return decoratedShape.getHeight();}
    public void draw(Graphics2D graphics2D){decoratedShape.draw(graphics2D);}
    public void drawOutline(Graphics2D graphics2D){decoratedShape.draw(graphics2D);}
    public void move(int newX, int newY){decoratedShape.move(newX, newY);}
    public void changeDimensions(int width, int height){decoratedShape.changeDimensions(width, height);};
    public IShape clone(){return decoratedShape.clone();}
}
