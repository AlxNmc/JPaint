package shapes;

import model.persistence.CanvasManager;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Group implements IShape {

    private List<IShape> members;

    public Group(){
        members = new ArrayList<>();
    }

    public Group(List<IShape> members){
        this.members = members;
    }

    public void add(IShape shape){
        members.add(shape);
    }

    public int getX() { return 0; }

    public int getY() { return 0; }

    public int getWidth() { return 0; }

    public int getHeight() { return 0; }

    public IShape clone() {
        List<IShape> clonedMembers = new ArrayList<>();
        for(IShape shape: members){ clonedMembers.add(shape.clone()); }
        return new Group(clonedMembers);
    }

    public ArrayDeque<IShape> getMembers() {
        return new ArrayDeque<>(members);
    }

    public void move(int xOffset, int yOffset) {
        for(IShape shape: members){
            shape.move(xOffset, yOffset);
        }
        drawSelected(CanvasManager.getG2DInstance());
    }

    public void draw(Graphics2D graphics2D) {
        for(IShape shape: members){
            shape.draw(graphics2D);
        }
    }

    public void drawSelected(Graphics2D graphics2D) {
        for(IShape shape: members){
            shape.drawSelected(graphics2D);
        }
    }
}
