package main;

import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import javax.swing.*;
import java.awt.*;
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
        System.out.println("Mouse Pressed at X:" + e.getX() + " Y:" + e.getY());
        lastPressX = e.getX();
        lastPressY = e.getY();
    }
    public void mouseReleased (MouseEvent e){
        System.out.println("Mouse Released at X:" + e.getX() + " Y:" + e.getY());
        drawRectangle(lastPressX, lastPressY, e.getX(), e.getY());
    }
    private void drawRectangle (int pressX, int pressY, int releaseX, int releaseY){
        int startX = Integer.min(pressX, releaseX);
        int startY = Integer.min(pressY, releaseY);
        int width = Math.abs(releaseX-pressX);
        int height = Math.abs(releaseY-pressY);

        System.out.println("sX:" + startX + " sY:" + startY + " width:" + width + " height:" + height);
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(startX, startY, Math.abs(releaseX-pressX), Math.abs(releaseY-pressY));
    }
}
