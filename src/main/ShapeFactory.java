package main;

import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class ShapeFactory implements  IShapeFactory {
    PaintCanvasBase canvas;
    IApplicationState state;
    IClipboard clipboard;

    ShapeFactory(PaintCanvasBase paintCanvasBase, IApplicationState applicationState, Clipboard clipboard){
        canvas = paintCanvasBase;
        state = applicationState;
        this.clipboard = clipboard;
    }

    public IShape createShape(int pressX, int pressY, int releaseX, int releaseY){
        IShape shape = null;
        ShapeType type = state.getActiveShapeType();
        switch (type){
            case RECTANGLE:
                shape = new Rectangle(pressX, pressY, releaseX, releaseY, state);
                break;
            case ELLIPSE:
                shape = new Ellipse(pressX, pressY, releaseX, releaseY, state);
                break;
            case TRIANGLE:
                shape = new Triangle(pressX, pressY, releaseX, releaseY, state);
                break;
        }
        shape.draw(canvas.getGraphics2D());
        clipboard.addShape(shape);
        return shape;
    }
}

interface IShapeFactory {
   IShape createShape(int pressX, int pressY, int releaseX, int releaseY);
}