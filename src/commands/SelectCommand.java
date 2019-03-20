package commands;

import model.persistence.CanvasManager;
import model.persistence.Clipboard;
import shapes.IShape;
import shapes.ShapeFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class SelectCommand implements ICommand {

    private Deque<IShape> previouslySelected;
    private Deque<IShape> shapes;

    public void run(int pressX, int pressY, int releaseX, int releaseY) {
        previouslySelected = new ArrayDeque<>();
        shapes = new ArrayDeque<>();
        IShape selection = ShapeFactory.createEmptyShape(pressX, releaseX, pressY, releaseY);
        getSelectedShapes();
        Clipboard.clearSelected();

        for(IShape group: CanvasManager.getShapes()) {
            //Perform collision check using members
            for(IShape member: group.getMembers()){
                if(collision(member, selection)){
                    Clipboard.addSelected(group);
                    shapes.addLast(group);
                    break;
                }
            }
        }
        CanvasManager.redraw();
        CommandHistory.add(this);
    }


    public void undo() {
        Clipboard.clearSelected();
        for(IShape shape: previouslySelected){
            Clipboard.addSelected(shape);
        }
        CanvasManager.redraw();
    }

    public void redo() {
        Clipboard.clearSelected();
        for(IShape shape: shapes){
            Clipboard.addSelected(shape);
        }
        CanvasManager.redraw();
    }

    private void getSelectedShapes(){
        for(IShape shape: Clipboard.getSelected()){
            previouslySelected.addLast(shape);
        }
    }

    private Boolean collision(IShape s1, IShape s2){
        return (s1.getX() < s2.getX() + s2.getWidth() &&
                s1.getX() + s1.getWidth() > s2.getX() &&
                s1.getY() < s2.getY() + s2.getHeight() &&
                s1.getY() + s1.getHeight() > s2.getY());
    }
}
