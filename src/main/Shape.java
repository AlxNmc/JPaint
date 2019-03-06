package main;

import model.ShapeShadingType;
import model.ShapeColor;

import java.awt.*;

public abstract class Shape implements IShape {
    private int startX;
    private int startY;
    private int width;
    private int height;
    private Color primaryColor = null;
    private Color secondaryColor = null;
    private ShapeShadingType shadingType = null;

    Shape(int pressX, int pressY, int releaseX, int releaseY){
        startX = Integer.min(pressX, releaseX);
        startY = Integer.min(pressY, releaseY);
        width = Math.abs(releaseX-pressX);
        height = Math.abs(releaseY-pressY);
    }

    public void setColors(Color primary, Color secondary){
        primaryColor = primary;
        secondaryColor = secondary;
    }

    public void setShadingType(ShapeShadingType shadingType){
        this.shadingType = shadingType;
    }

    public int getX(){return startX;}
    public int getY(){return startY;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public ShapeShadingType getShadingType() {return shadingType;}
    public Color getPrimaryColor() {return primaryColor;}
    public Color getSecondaryColor() { return secondaryColor; }

    //draws the shape to the canvas based on the appropriate shading type
    public void draw(Graphics2D graphics2D) {
        ShapeShadingType shadingType = getShadingType();
        switch (shadingType){
            case FILLED_IN:
                graphics2D.setColor(getPrimaryColor());
                drawFilled(graphics2D);
                break;
            case OUTLINE:
                graphics2D.setColor(getPrimaryColor());
                setStroke(graphics2D);
                drawOutline(graphics2D);
                break;
            case OUTLINE_AND_FILLED_IN:
                graphics2D.setColor(getPrimaryColor());
                drawFilled(graphics2D);
                graphics2D.setColor(getSecondaryColor());
                setStroke(graphics2D);
                drawOutline(graphics2D);
        }
    }

    public void drawOutline(Graphics2D graphics2D){
        //meant to be overloaded by subclasses
    }
    public void setStroke(Graphics2D graphics2D){
        graphics2D.setStroke(new BasicStroke(5));
    }
    public void drawFilled(Graphics2D graphics2D) {
        //meant to be overloaded by subclasses
    }
    public void move(int xOffset, int yOffset){
        startX = getX() + xOffset;
        startY = getY() + yOffset;
    }

    public IShape clone(){ return null; }
}
