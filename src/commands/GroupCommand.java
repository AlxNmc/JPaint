package commands;

import model.persistence.CanvasManager;
import model.persistence.Clipboard;
import shapes.Group;
import shapes.IShape;

import java.util.Deque;

public class GroupCommand implements ICommand {

    private Group group;
    private Deque<IShape> selected;

    public void run(){
        group = new Group();
        selected = Clipboard.getSelected();
        groupSelected();
        CommandHistory.add(this);
    }

    public void undo(){
        CanvasManager.removeShape(group);
        Deque<IShape> shapes= group.getMembers();
        for(IShape member: shapes){
            CanvasManager.addShape(member);
        }
    }

    public void redo(){
        groupSelected();
    }

    private void groupSelected(){
        Clipboard.clearSelected();
        for (IShape shape: selected){
            CanvasManager.removeShape(shape);
            group.add(shape);
        }
        Clipboard.addSelected(group);
        CanvasManager.addShape(group);
    }
}
