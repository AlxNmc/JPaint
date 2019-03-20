package shapes;

import model.ShapeColor;
import model.interfaces.IApplicationState;

import java.awt.*;
import java.util.EnumMap;

public class ShapeFactory {

    private static EnumMap<ShapeColor, Color> colorMap;

    public static Shape createShape(IApplicationState state, int pressX, int releaseX, int pressY, int releaseY){
        int[] d = getDimensions(pressX, releaseX, pressY, releaseY);
        Color primary = getColor(state.getActivePrimaryColor());
        Color secondary = getColor(state.getActiveSecondaryColor());
        IShapeStrategy strategy = getStrategy(state);

        return new Shape(strategy, primary, secondary, state.getActiveShapeShadingType(), d[0], d[1], d[2], d[3]);
    }

    public static Shape createEmptyShape(int pressX, int releaseX, int pressY, int releaseY){
        int[] d = getDimensions(pressX, releaseX, pressY, releaseY);
        return new Shape(d[0], d[1], d[2], d[3]);
    }

    private static IShapeStrategy getStrategy(IApplicationState state){
        switch (state.getActiveShapeType()){
            case RECTANGLE:
                return new RectangleStrategy();
            case TRIANGLE:
                return new TriangleStrategy();
            case ELLIPSE:
                return new EllipseStrategy();
        }
        return null;
    }

    //returns array: [X, Y, width, height]
    private static int[] getDimensions(int pressX, int releaseX, int pressY, int releaseY){
        int[] d = new int[4];
        d[0] = Integer.min(pressX, releaseX);
        d[1] = Integer.min(pressY, releaseY);
        d[2] = Math.abs(releaseX-pressX);
        d[3] = Math.abs(releaseY-pressY);
        return d;
    }

    private static Color getColor(ShapeColor shapeColor){
        if(colorMap==null){
            fillColorMap();
        }
        return colorMap.get(shapeColor);
    }

    private static void fillColorMap(){
        colorMap = new EnumMap<>(ShapeColor.class);
        colorMap.put(ShapeColor.BLACK, Color.BLACK);
        colorMap.put(ShapeColor.BLUE, Color.BLUE);
        colorMap.put(ShapeColor.CYAN, Color.CYAN);
        colorMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        colorMap.put(ShapeColor.GRAY, Color.GRAY);
        colorMap.put(ShapeColor.GREEN, Color.GREEN);
        colorMap.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        colorMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
        colorMap.put(ShapeColor.ORANGE, Color.ORANGE);
        colorMap.put(ShapeColor.PINK, Color.PINK);
        colorMap.put(ShapeColor.RED, Color.RED);
        colorMap.put(ShapeColor.WHITE, Color.WHITE);
        colorMap.put(ShapeColor.YELLOW, Color.YELLOW);
    }
}
