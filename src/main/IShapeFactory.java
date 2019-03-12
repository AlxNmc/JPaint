package main;

public interface IShapeFactory {
    IShape createShape(int pressX, int pressY, int releaseX, int releaseY);
}