package main;

import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class ShapeFactory implements  IShapeFactory {
    PaintCanvasBase canvas;

    ShapeFactory(PaintCanvasBase canvas){
        this.canvas = canvas;
    }

    public IShape createRectangle(int pressX, int pressY, int releaseX, int releaseY){
        Rectangle rectangle = new Rectangle(pressX,pressY,releaseX,releaseY);

        //draw to canvas
        Graphics2D graphics2d = canvas.getGraphics2D();
        graphics2d.setColor(Color.BLUE);
        graphics2d.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

        return rectangle;
    }
}

interface IShapeFactory {
   IShape createRectangle(int pressX, int pressY, int releaseX, int releaseY);
}