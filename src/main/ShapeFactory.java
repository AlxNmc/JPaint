package main;

import model.ShapeType;
import model.interfaces.IApplicationState;

public class ShapeFactory implements  IShapeFactory {
    private IApplicationState state;
    private IAbstractCanvas abstractCanvas;

    ShapeFactory(IApplicationState applicationState, IAbstractCanvas abstractCanvas){
        state = applicationState;
        this.abstractCanvas = abstractCanvas;
    }

    public IShape createShape(int pressX, int pressY, int releaseX, int releaseY){
        IShape shape = null;
        ShapeType type = state.getActiveShapeType();
        StateTranslator translator = new StateTranslator();
        switch (type){
            case RECTANGLE:
                shape = new Rectangle(pressX, pressY, releaseX, releaseY);
                break;
            case ELLIPSE:
                shape = new Ellipse(pressX, pressY, releaseX, releaseY);
                break;
            case TRIANGLE:
                shape = new Triangle(pressX, pressY, releaseX, releaseY);
                break;
        }

        shape.setColors(translator.getAWTColor(state.getActivePrimaryColor())
                        , translator.getAWTColor(state.getActiveSecondaryColor()));
        shape.setShadingType(state.getActiveShapeShadingType());

        abstractCanvas.addShape(shape);
        return shape;
    }
}