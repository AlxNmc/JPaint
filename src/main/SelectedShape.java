package main;

import java.awt.*;

public class SelectedShape extends ShapeDecorator{
    public SelectedShape(IShape selectedShape){
        super(selectedShape);
    }

    @Override
    public void draw(Graphics2D graphics2D){
        decoratedShape.draw(graphics2D);
        setSelectionStroke(graphics2D);
        decoratedShape.drawOutline(graphics2D);
    }

    private void setSelectionStroke(Graphics2D graphics2D){
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
    }
}