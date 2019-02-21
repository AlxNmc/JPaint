package main;

import model.interfaces.IApplicationState;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {

    Ellipse(int pressX, int pressY, int releaseX, int releaseY, IApplicationState applicationState){
        super(pressX, pressY, releaseX, releaseY, applicationState);
    }
    @Override
    public void drawOutline(Graphics2D graphics2D){
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.draw(new Ellipse2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
    }
    @Override
    public void drawFilled(Graphics2D graphics2D) {
        graphics2D.fill(new Ellipse2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
    }
}
