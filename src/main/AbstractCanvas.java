package main;

import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;
import java.util.ArrayDeque;
import java.util.Deque;

public class AbstractCanvas implements IAbstractCanvas {
    IApplicationState state;
    PaintCanvasBase canvas;
    IClipboard clipboard;

    Deque<IShape> shapes;

    AbstractCanvas(IApplicationState state, PaintCanvasBase canvas, Clipboard clipboard){
        this.clipboard = clipboard;
        this.state = state;
        this.canvas = canvas;
        shapes = new ArrayDeque<>();
    }

    public void addShape(IShape shape){
        shape.draw(canvas.getGraphics2D());
        shapes.addLast(shape);
    }

    public void selectShapes(int pressX, int pressY, int releaseX, int releaseY){
        IShape selection = new Rectangle(pressX, pressY, releaseX, releaseY);
        clipboard.clearSelected();
        canvas.paintImmediately(0, 0, canvas.getWidth(), canvas.getHeight());
        for (IShape shape: shapes) {
            if(collision(shape, selection)){
                clipboard.addSelected(shape);
                drawSelected(shape);
            }else{
                shape.draw(canvas.getGraphics2D());
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

    public void moveShapes(int pressX, int pressY, int releaseX, int releaseY){
        int xOffset = releaseX - pressX;
        int yOffset = releaseY - pressY;

        canvas.paintImmediately(0, 0, canvas.getWidth(), canvas.getHeight());

        for(IShape shape: shapes){
            if(clipboard.getSelected().contains(shape)){
                shape.move(xOffset, yOffset);
                drawSelected(shape);
            }
            shape.draw(canvas.getGraphics2D());
        }
    }

    private void drawSelected(IShape shape){
        IShape selectedShape = new SelectedShape(shape);
        selectedShape.draw(canvas.getGraphics2D());
    }

    public void paste(){
        clipboard.clearSelected();
        for(IShape shape: clipboard.getCopied()){
            IShape newShape = shape.clone();
            newShape.move(10, 10);
            drawSelected(newShape);
            addShape(newShape);
            clipboard.addSelected(newShape);
        }
    }

    public void deleteSelected(){
        canvas.paintImmediately(0, 0, canvas.getWidth(), canvas.getHeight());
        for(IShape shape: shapes){
            if (clipboard.getSelected().contains(shape)){
                shapes.remove(shape);
            }else{
                shape.draw(canvas.getGraphics2D());
            }
        }
        clipboard.clearSelected();
    }

}

interface IAbstractCanvas {
    void addShape(IShape shape);
    void selectShapes(int pressX, int pressY, int releaseX, int releaseY);
    void moveShapes(int pressX, int pressY, int releaseX, int releaseY);
    void paste();
    void deleteSelected();
}