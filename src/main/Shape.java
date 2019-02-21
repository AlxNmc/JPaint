package main;

import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IApplicationState;

import java.awt.*;

public class Shape implements IShape {
    private int startX;
    private int startY;
    private int width;
    private int height;
    private ShapeType shapeType;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeShadingType shadingType;

    Shape(int pressX, int pressY, int releaseX, int releaseY, IApplicationState state){
        startX = Integer.min(pressX, releaseX);
        startY = Integer.min(pressY, releaseY);
        width = Math.abs(releaseX-pressX);
        height = Math.abs(releaseY-pressY);
        if(state!=null) //allows for creation of a "bounding box only" shape for selection box
            setStateVariables(state);
    }

    private void setStateVariables(IApplicationState state){
        StateTranslator translator = new StateTranslator();
        shapeType = state.getActiveShapeType();
        primaryColor = translator.getAWTColor(state.getActivePrimaryColor());
        secondaryColor = translator.getAWTColor(state.getActiveSecondaryColor());
        shadingType = state.getActiveShapeShadingType();
    }

    public int getX(){return startX;}
    public int getY(){return startY;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public ShapeType getShapeType() {return shapeType;}
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
                drawOutline(graphics2D);
                break;
            case OUTLINE_AND_FILLED_IN:
                graphics2D.setColor(getPrimaryColor());
                drawFilled(graphics2D);
                graphics2D.setColor(getSecondaryColor());
                drawOutline(graphics2D);
        }
    }
    public void drawOutline(Graphics2D graphics2D){} //meant to be overloaded by subclasses
    public void drawFilled(Graphics2D graphics2D) {} //meant to be overloaded by subclasses
    public void move(int xOffset, int yOffset){
        startX = getX() + xOffset;
        startY = getY() + yOffset;
    }
}
