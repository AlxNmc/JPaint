package main;

import model.interfaces.IApplicationState;

import java.awt.*;

public class Rectangle extends Shape{

    Rectangle(int pressX, int pressY, int releaseX, int releaseY, IApplicationState applicationState){
        super(pressX, pressY, releaseX, releaseY, applicationState);
    }
    @Override
    public void drawOutline(Graphics2D graphics2D){
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    @Override
    public void drawFilled(Graphics2D graphics2D) {
        graphics2D.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

}
