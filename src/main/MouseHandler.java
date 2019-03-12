package main;

import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private IShapeFactory shapeFactory;
    private IApplicationState state;
    private IAbstractCanvas abstractCanvas;
    private int lastPressX;
    private int lastPressY;

    public MouseHandler(ShapeFactory shapeFactory, IApplicationState state, AbstractCanvas abstractCanvas){
        this.shapeFactory = shapeFactory;
        this.state = state;
        this.abstractCanvas = abstractCanvas;
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

    //handles action to take after mouse is clicked and released
    private void dragEvent (int pressX, int pressY, int releaseX, int releaseY){
        StartAndEndPointMode mode = state.getActiveStartAndEndPointMode();
        switch (mode){
            case DRAW:
                shapeFactory.createShape(pressX, pressY, releaseX, releaseY);
                break;
            case SELECT:
                abstractCanvas.selectShapes(pressX, pressY, releaseX, releaseY);
                break;
            case MOVE:
                abstractCanvas.moveShapes(pressX, pressY, releaseX, releaseY);
                break;
        }
    }
}
