package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private IShapeFactory shapeFactory;
    private int lastPressX;
    private int lastPressY;

    public MouseHandler(ShapeFactory shapeFactory){
        this.shapeFactory = shapeFactory;
        lastPressX = 0;
        lastPressY = 0;
    }
    public void mousePressed (MouseEvent e){
        lastPressX = e.getX();
        lastPressY = e.getY();
    }
    public void mouseReleased (MouseEvent e){

        dragEvent(lastPressX, lastPressY, e.getX(), e.getY());
    }
    private void dragEvent (int pressX, int pressY, int releaseX, int releaseY){
        shapeFactory.createShape(pressX, pressY, releaseX, releaseY);
    }
}
