package main;

import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class ShapeFactory implements  IShapeFactory {
    PaintCanvasBase canvas;
    IApplicationState state;

    ShapeFactory(PaintCanvasBase paintCanvasBase, IApplicationState applicationState){
        canvas = paintCanvasBase;
        state = applicationState;
    }

    public IShape createShape(int pressX, int pressY, int releaseX, int releaseY){
        IShape shape = null;
        ShapeType type = state.getActiveShapeType();
        switch (type){
            case RECTANGLE:
                Rectangle rectangle = new Rectangle(pressX, pressY, releaseX, releaseY, state);
                rectangle.draw(canvas.getGraphics2D());
                shape = rectangle;
                break;
            case ELLIPSE:
                Ellipse ellipse = new Ellipse(pressX, pressY, releaseX, releaseY, state);
                ellipse.draw(canvas.getGraphics2D());
                shape = ellipse;
                break;
            case TRIANGLE:
                Triangle triangle = new Triangle(pressX, pressY, releaseX, releaseY, state);
                triangle.draw(canvas.getGraphics2D());
                shape = triangle;
                break;
        }
        return shape;
    }
}

interface IShapeFactory {
   IShape createShape(int pressX, int pressY, int releaseX, int releaseY);
}