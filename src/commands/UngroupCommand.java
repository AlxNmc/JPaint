package commands;

import model.persistence.CanvasManager;
import model.persistence.Clipboard;
import shapes.IShape;

import java.util.ArrayDeque;
import java.util.Deque;

public class UngroupCommand implements ICommand {

    private Deque<IShape> groups;
    private Deque<IShape> ungrouped;

    public void run() {
        groups = Clipboard.getSelected();
        ungrouped = new ArrayDeque<>();
        ungroup();
        CommandHistory.add(this);
    }

    public void undo() {
        Clipboard.clearSelected();
        for(IShape shape: ungrouped){
            CanvasManager.removeShape(shape);
        }
        for(IShape group: groups){
            CanvasManager.addShape(group);
            Clipboard.addSelected(group);
        }
        CanvasManager.redraw();
    }

    public void redo() {
        ungroup();
    }

    private void ungroup(){
        Clipboard.clearSelected();
        for(IShape group: groups){
            Deque<IShape> members = group.getMembers();
            CanvasManager.removeShape(group);
            for(IShape member: members){
                CanvasManager.addShape(member);
                Clipboard.addSelected(member);
                ungrouped.addLast(member);
            }
        }
        CanvasManager.redraw();
    }

}
