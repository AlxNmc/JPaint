package main;

import java.awt.*;

public class Rectangle extends Shape{

    Rectangle(int pressX, int pressY, int releaseX, int releaseY){
        super(pressX, pressY, releaseX, releaseY);
    }
    @Override
    public void drawOutline(Graphics2D graphics2D){
        graphics2D.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    @Override
    public void drawFilled(Graphics2D graphics2D) {
        graphics2D.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    @Override
    public IShape clone(){
        IShape newRect = new Rectangle(getX(), getY(), getX()+getWidth(), getY()+getHeight());
        newRect.setColors(getPrimaryColor(), getSecondaryColor());
        newRect.setShadingType(getShadingType());
        return newRect;
    }
}
