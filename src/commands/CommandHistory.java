package commands;

import java.util.Stack;

public class CommandHistory {
    private static final Stack<ICommand> undoStack = new Stack<ICommand>();
    private static final Stack<ICommand> redoStack = new Stack<ICommand>();

    public static void add(ICommand command){
        undoStack.push(command);
        redoStack.clear();
    }
    public static Boolean undo(){
        boolean result = !undoStack.empty();
        if (result) {
            ICommand c = undoStack.pop();
            redoStack.push(c);
            c.undo();
        }
        return result;
    }
    public static Boolean redo(){
        boolean result = !redoStack.empty();
        if(result){
            ICommand c = redoStack.pop();
            undoStack.push(c);
            c.redo();
        }
        return result;
    }
}
