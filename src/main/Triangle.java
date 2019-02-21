package main;

import model.interfaces.IApplicationState;

import java.awt.*;

public class Triangle extends Shape{

    private int[] xpoints;
    private int[] ypoints;

    Triangle(int pressX, int pressY, int releaseX, int releaseY, IApplicationState applicationState){
        super(pressX, pressY, releaseX, releaseY, applicationState);
        xpoints = new int[3];
        ypoints = new int[3];
        setPoints();
    }

    private void setPoints(){
        xpoints[0] = getX();
        ypoints[0] = getY() + getHeight();
        xpoints[1] = getX() + getWidth()/2;
        ypoints[1] = getY();
        xpoints[2] = getX() + getWidth();
        ypoints[2] = getY() + getHeight();
    }

    @Override
    public void drawOutline(Graphics2D graphics2D){
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawPolygon(xpoints, ypoints, 3);
    }
    @Override
    public void drawFilled(Graphics2D graphics2D) {
        graphics2D.fillPolygon(xpoints, ypoints, 3);
    }
}
