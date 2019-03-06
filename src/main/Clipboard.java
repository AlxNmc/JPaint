package main;

import java.util.ArrayDeque;
import java.util.Deque;

public class Clipboard implements IClipboard{

    private Deque<IShape> copiedShapes;
    private Deque<IShape> selectedShapes;

    public Clipboard(){
        copiedShapes = new ArrayDeque<>();
        selectedShapes = new ArrayDeque<>();
    }

    @Override
    public void addSelected(IShape shape) {
        selectedShapes.addLast(shape);
    }

    @Override
    public void copy() {
        copiedShapes.clear();
        for(IShape shape: selectedShapes){ copiedShapes.addLast(shape); }
    }

    @Override
    public void clearSelected() {
        selectedShapes = new ArrayDeque<>();
    }

    @Override
    public Deque<IShape> getSelected() {
        return selectedShapes;
    }

    @Override
    public Deque<IShape> getCopied() {
        return copiedShapes;
    }
}

interface IClipboard {
    void addSelected(IShape shapes);
    void copy();
    void clearSelected();
    Deque<IShape> getSelected();
    Deque<IShape> getCopied();
}