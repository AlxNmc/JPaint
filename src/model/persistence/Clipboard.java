package model.persistence;

import shapes.IShape;

import java.util.ArrayDeque;
import java.util.Deque;

public class Clipboard {

    private  Deque<IShape> copiedShapes = new ArrayDeque<>();
    private  Deque<IShape> selectedShapes = new ArrayDeque<>();

    private static Clipboard instance = new Clipboard();
    private Clipboard(){}

    public static void addSelected(IShape shape) {
        instance.selectedShapes.addLast(shape);
    }

    public static void addCopied(IShape shape) { instance.copiedShapes.addLast(shape); }

    public static void clearSelected() { instance.selectedShapes.clear(); }

    public static void clearCopied(){
        instance.selectedShapes.clear();
    }

    public static Deque<IShape> getSelected() {
        return new ArrayDeque(instance.selectedShapes);
    }

    public static Deque<IShape> getCopied() { return new ArrayDeque(instance.copiedShapes); }
}