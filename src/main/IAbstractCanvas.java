package main;

public interface IAbstractCanvas {
    void addShape(IShape shape);
    void selectShapes(int pressX, int pressY, int releaseX, int releaseY);
    void moveShapes(int pressX, int pressY, int releaseX, int releaseY);
    void paste();
    void deleteSelected();
}
