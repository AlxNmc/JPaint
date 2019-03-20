package commands;

import model.persistence.CanvasManager;
import shapes.IShape;

public class CreateCommand implements ICommand {

    private IShape shape;

    public void run(IShape shape){
        this.shape = shape;
        shape.draw(CanvasManager.getG2DInstance());
        CanvasManager.addShape(shape);
        CommandHistory.add(this);
    }
    public void undo(){
        CanvasManager.removeShape(shape);
        CanvasManager.redraw();
    }
    public void redo(){
        shape.draw(CanvasManager.getG2DInstance());
        CanvasManager.addShape(shape);
    }
}
