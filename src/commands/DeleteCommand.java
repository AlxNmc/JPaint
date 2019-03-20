package commands;

import model.persistence.CanvasManager;
import model.persistence.Clipboard;
import shapes.IShape;

import java.util.Deque;

public class DeleteCommand implements ICommand {

    private Deque<IShape> toDelete;

    public void run(){
        toDelete = Clipboard.getSelected();
        delete();
        CommandHistory.add(this);
    }

    public void undo() {
        for(IShape shape: toDelete){
            CanvasManager.addShape(shape);
            Clipboard.addSelected(shape);
        }
        CanvasManager.redraw();
    }

    public void redo() {
        delete();
    }

    private void delete(){
        Clipboard.clearSelected();
        for(IShape shape: toDelete){
            CanvasManager.removeShape(shape);
        }
        CanvasManager.redraw();
    }
}
