package main;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {

    Ellipse(int pressX, int pressY, int releaseX, int releaseY){
        super(pressX, pressY, releaseX, releaseY);
    }
    @Override
    public void drawOutline(Graphics2D graphics2D){
        graphics2D.draw(new Ellipse2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
    }
    @Override
    public void drawFilled(Graphics2D graphics2D) {
        graphics2D.fill(new Ellipse2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
    }
    @Override
    public IShape clone(){
        IShape newEllipse = new Ellipse(getX(), getY(), getX()+getWidth(), getY()+getHeight());
        newEllipse.setColors(getPrimaryColor(), getSecondaryColor());
        newEllipse.setShadingType(getShadingType());
        return newEllipse;
    }
}
