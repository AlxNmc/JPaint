package model.persistence;

import shapes.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class CanvasManager {
    private static PaintCanvasBase canvas;
    private static Deque<IShape> shapes;

    CanvasManager(PaintCanvasBase canvas){
        if (shapes==null){ shapes = new ArrayDeque<>(); }
        if(this.canvas == null){ this.canvas = canvas; }
    }

    public static Graphics2D getG2DInstance(){ return canvas.getGraphics2D(); }

    public static Deque<IShape> getShapes(){ return new ArrayDeque<>(shapes); }

    public static void addShape(IShape shape){ shapes.addLast(shape); }

    public static void removeShape(IShape shape) { shapes.remove(shape); }


    private static void drawSelected(IShape shape){
        shape.drawSelected(canvas.getGraphics2D());
    }

    public static void redraw(){
        canvas.paintImmediately(0, 0, canvas.getWidth(), canvas.getHeight());
        for(IShape shape: shapes){
            if(Clipboard.getSelected().contains(shape)){
                drawSelected(shape);
            }else{
                shape.draw(canvas.getGraphics2D());
            }
        }
    }

}