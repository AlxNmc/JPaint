package main;

import java.awt.*;

public class Triangle extends Shape{

    private int[] xpoints; //x coordinates of all vertices
    private int[] ypoints; //y coordinates of all vertices

    Triangle(int pressX, int pressY, int releaseX, int releaseY){
        super(pressX, pressY, releaseX, releaseY);
        xpoints = new int[3];
        ypoints = new int[3];
        setPoints(getWidth(), getHeight());
    }

    private void setPoints(int width, int height){
        xpoints[0] = getX();
        ypoints[0] = getY() + height;
        xpoints[1] = getX() + width/2;
        ypoints[1] = getY();
        xpoints[2] = getX() + width;
        ypoints[2] = getY() + height;
    }

    @Override
    public void drawOutline(Graphics2D graphics2D){
        graphics2D.drawPolygon(xpoints, ypoints, 3);
    }
    @Override
    public void drawFilled(Graphics2D graphics2D) {
        graphics2D.fillPolygon(xpoints, ypoints, 3);
    }
    @Override
    //Needed for the special point-array method of shape creation
    public void move(int xOffset, int yOffset) {
        super.move(xOffset, yOffset);
        setPoints(getWidth(), getHeight());
    }
    @Override
    public void changeDimensions(int width, int height){
        super.changeDimensions(width, height);
        setPoints(getWidth(), getHeight());
    }
    @Override
    public IShape clone(){
        IShape newTriangle = new Triangle(getX(), getY(), getX()+getWidth(), getY()+getHeight());
        newTriangle.setColors(getPrimaryColor(), getSecondaryColor());
        newTriangle.setShadingType(getShadingType());
        return newTriangle;
    }
}
