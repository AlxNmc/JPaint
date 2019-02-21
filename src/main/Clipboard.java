package main;

import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Clipboard implements IClipboard{
    IApplicationState state;
    PaintCanvasBase canvas;
    private Deque<IShape> activeShapes; //stores shapes currently rendered on canvas
    private Deque<IShape> selectedShapes; //stores currently selected shaapes

    Clipboard(IApplicationState state, PaintCanvasBase canvas){
        activeShapes = new ArrayDeque<>();
        selectedShapes = new ArrayDeque<>();
        this.state = state;
        this.canvas = canvas;
    }

    public void addShape(IShape shape){
        activeShapes.addLast(shape);
    }

    //uses a temporary rectangle shape to check for collisions with active shapes on canvas.
    //adds selected shapes to selectedShapes deque.
    public void selectShapes(int pressX, int pressY, int releaseX, int releaseY){
        IShape selection = new Rectangle(pressX, pressY, releaseX, releaseY, null);
        selectedShapes.clear();
        for (IShape shape: activeShapes) {
            if(collision(shape, selection)){
                selectedShapes.addLast(shape);
            }
        }
    }

    //simple collision detection algorithm. Uses bounding boxes of both shapes for the calculation.
    private Boolean collision(IShape s1, IShape s2){
        return (s1.getX() < s2.getX() + s2.getWidth() &&
                s1.getX() + s1.getWidth() > s2.getX() &&
                s1.getY() < s2.getY() + s2.getHeight() &&
                s1.getY() + s1.getHeight() > s2.getY());
    }

    //applies an offset to the selected shapes
    public void moveShapes(int pressX, int pressY, int releaseX, int releaseY){
        int xOffset = releaseX - pressX;
        int yOffset = releaseY - pressY;

        //apply the offset and move the shape to the top of the deque
        for(IShape shape: selectedShapes){
            shape.move(xOffset, yOffset);
            activeShapes.remove(shape);
            activeShapes.addLast(shape);
        }

        //repaint the canvas
        canvas.paintImmediately(0, 0, canvas.getWidth(), canvas.getHeight());

        //redraw shapes from activeShapes deque
        for(IShape shape: activeShapes){
            shape.draw(canvas.getGraphics2D());
        }
    }
}

interface IClipboard {
    void addShape(IShape shape);
    void selectShapes(int pressX, int pressY, int releaseX, int releaseY);
    void moveShapes(int pressX, int pressY, int releaseX, int releaseY);
}