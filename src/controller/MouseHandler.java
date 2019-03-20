package controller;

import commands.CreateCommand;
import commands.MoveCommand;
import commands.SelectCommand;
import shapes.ShapeFactory;
import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private IApplicationState state;
    private int lastPressX;
    private int lastPressY;

    public MouseHandler(IApplicationState state){
        this.state = state;
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
                new CreateCommand().run(ShapeFactory.createShape(state, pressX, releaseX, pressY, releaseY));
                //shapeFactory.createShape(pressX, pressY, releaseX, releaseY);
                break;
            case SELECT:
                new SelectCommand().run(pressX, pressY, releaseX, releaseY);
                break;
            case MOVE:
                new MoveCommand().run(pressX, pressY, releaseX, releaseY);
                break;
        }
    }
}
