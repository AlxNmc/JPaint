package main;

public class Rectangle implements IShape{

    private int startX;
    private int startY;
    private int width;
    private int height;

    Rectangle(int pressX, int pressY, int releaseX, int releaseY){
        startX = Integer.min(pressX, releaseX);
        startY = Integer.min(pressY, releaseY);
        width = Math.abs(releaseX-pressX);
        height = Math.abs(releaseY-pressY);
    }

    public int getX(){return startX;}
    public int getY(){return startY;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}

}
