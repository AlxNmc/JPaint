package main;

import java.util.Deque;

public interface IClipboard {
    void addSelected(IShape shapes);
    void copy();
    void clearSelected();
    Deque<IShape> getSelected();
    Deque<IShape> getCopied();
}
