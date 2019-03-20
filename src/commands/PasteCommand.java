package commands;

import model.persistence.CanvasManager;
import model.persistence.Clipboard;
import shapes.IShape;

import java.util.ArrayDeque;
import java.util.Deque;

public class PasteCommand implements ICommand {

    private Iterable<IShape> previouslySelected;
    private Deque<IShape> copied;
    private Deque<IShape> pasted;

    public void run(){
        previouslySelected = Clipboard.getSelected();
        copied = Clipboard.getCopied();
        pasted = new ArrayDeque<>();
        paste();
        CommandHistory.add(this);
    }

    public void undo() {
        Clipboard.clearSelected();
        for(IShape shape: CanvasManager.getShapes()){
            if (pasted.contains(shape)){
                CanvasManager.removeShape(shape);
            }
        }
        for(IShape shape: previouslySelected){
            Clipboard.addSelected(shape);
        }
        CanvasManager.redraw();
    }

    public void redo() {
        paste();
    }

    private void paste(){
        Clipboard.clearSelected();
        pasted.clear();
        for(IShape shape: copied){
            IShape copy = shape.clone();
            copy.move(15, 15);
            CanvasManager.addShape(copy);
            Clipboard.addSelected(copy);
            pasted.add(copy);
        }
        CanvasManager.redraw();
    }
}
