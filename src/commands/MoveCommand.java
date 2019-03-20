package commands;

import model.persistence.CanvasManager;
import model.persistence.Clipboard;
import shapes.IShape;

public class MoveCommand implements ICommand {

    private Iterable<IShape> _shapes;
    private int _xOffset;
    private int _yOffset;

    public void run(int pressX, int pressY, int releaseX, int releaseY){
        _shapes = Clipboard.getSelected();
        _xOffset = releaseX - pressX;
        _yOffset = releaseY - pressY;

        moveShapes(_xOffset, _yOffset);
        CommandHistory.add(this);
    }
    public void undo(){
        moveShapes(-_xOffset, -_yOffset);
    }
    public void redo(){
        moveShapes(_xOffset, _yOffset);
    }

    private void moveShapes(int xOffset, int yOffset){
        for(IShape shape: _shapes){
            shape.move(xOffset, yOffset);
        }
        CanvasManager.redraw();
    }
}
