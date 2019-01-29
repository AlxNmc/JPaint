package main;

import view.interfaces.PaintCanvasBase;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private PaintCanvasBase paintCanvas;
    private int lastPressX;
    private int lastPressY;

    public MouseHandler(PaintCanvasBase paintCanvas){
        this.paintCanvas = paintCanvas;
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
        IShapeFactory shapeFactory = new ShapeFactory(paintCanvas);
        shapeFactory.createRectangle(pressX, pressY, releaseX, releaseY);
    }
}
