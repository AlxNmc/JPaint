package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

//TODO write some unit tests

//TODO organize my classes into appropriate packages outside of main
public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        Clipboard clipboard = new Clipboard();
        AbstractCanvas abstractCanvas = new AbstractCanvas(paintCanvas, clipboard);
        IJPaintController controller = new JPaintController(uiModule, appState, clipboard, abstractCanvas);
        controller.setup();

        ShapeFactory shapeFactory = new ShapeFactory(appState, abstractCanvas);
        MouseHandler mouseHandler = new MouseHandler(shapeFactory, appState, abstractCanvas);
        paintCanvas.addMouseListener(mouseHandler);
    }
}
