package commands;

import model.persistence.Clipboard;
import shapes.IShape;

public class CopyCommand implements ICommand {

    private Iterable<IShape> copiedShapes;

    public void run(){
        copiedShapes = Clipboard.getSelected();
        Clipboard.clearCopied();
        for(IShape shape: copiedShapes){
            Clipboard.addCopied(shape);
        }
    }

    public void undo() { }
    public void redo() { }
}
