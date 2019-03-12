package main;

import view.interfaces.PaintCanvasBase;
import java.util.ArrayDeque;
import java.util.Deque;

//TODO Make a redraw class that other classes can use when making changes to the canvas

public class AbstractCanvas implements IAbstractCanvas {
    private PaintCanvasBase canvas;
    private IClipboard clipboard;
    private Deque<IShape> shapes;

    AbstractCanvas(PaintCanvasBase canvas, IClipboard clipboard){
        this.clipboard = clipboard;
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
        for (IShape shape: shapes) {
            if(collision(shape, selection)){
                clipboard.addSelected(shape);
            }
        }
        redraw();
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

        for(IShape shape: shapes){
            if(clipboard.getSelected().contains(shape)){
                shape.move(xOffset, yOffset);
            }
        }
        redraw();
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
            shapes.add(newShape);
            clipboard.addSelected(newShape);
        }
        redraw();
    }

    public void deleteSelected(){
        for(IShape shape: clipboard.getSelected()){
            shapes.remove(shape);
        }
        clipboard.clearSelected();
        redraw();
    }

    private void redraw(){
        canvas.paintImmediately(0, 0, canvas.getWidth(), canvas.getHeight());
        for(IShape shape: shapes){
            if(clipboard.getSelected().contains(shape)){
                drawSelected(shape);
            }else{
                shape.draw(canvas.getGraphics2D());
            }
        }
    }

}